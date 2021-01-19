package org.endeavourhealth.getFHIRRecordAPI.api.endpoints;

import ca.uhn.fhir.context.FhirContext;
import com.fasterxml.jackson.databind.JsonNode;
import models.Parameter;
import models.Params;
import models.Request;
import models.ResourceNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.endeavourhealth.common.config.ConfigManager;
import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.endeavourhealth.getFHIRRecordAPI.common.dal.JDBCDAL;
import org.endeavourhealth.getFHIRRecordAPI.common.models.*;
import org.hl7.fhir.dstu3.model.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resources.AllergyIntolerance;
import resources.Appointment;
import resources.Condition;
import resources.DiagnosticReport;
import resources.Encounter;
import resources.EpisodeOfCare;
import resources.FamilyMemberHistory;
import resources.Immunization;
import resources.Location;
import resources.MedicationStatement;
import resources.Observation;
import resources.Organization;
import resources.Patient;
import resources.Practitioner;
import resources.PractitionerRole;
import resources.Procedure;
import resources.ReferralRequest;
import resources.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FhirApi {
    private static final Logger LOG = LoggerFactory.getLogger(FhirApi.class);
    private static final String CONFIG_ID_RUN_MODE = "run_mode";
    private static final String CONFIG_ID_RUN_MODE_TEST = "test";

    HashMap<Long, Resource> organizationFhirMap;
    HashMap<Long, Resource> encounterFhirMap;
    Map<Long, List<Resource>> practitionerAndRoleResource;
    Map<Long, Coding> patientCodingMap;
    Map<String, Resource> episodeOfCareResourceMap;
    JsonNode jsonTestNHSIdMappings;
    String originalNHSNumber = null;
    String runMode = null;
    Set<String> observationIds;

    Bundle bundle;
    org.hl7.fhir.dstu3.model.Patient patientResource;
    public JSONObject handleRequest(Request request) throws ResourceNotFoundException {
        JSONObject json = null;
        JSONObject json1 = null;
        List<JSONObject>  jsonObjectList = new ArrayList<>();
        switch (request.getHttpMethod()) {
            case "POST":
                Params params = request.getParams();
                List<Parameter> parameters = params.getParameter();

                String nhsNumber = "0";
                String dateOfBirth = "0";
                boolean onlyDemographics = false;
                boolean activePatientsOnly = false;

                for (Parameter param : parameters) {
                    String paramName = param.getName();
                    if (paramName.equals("patientNHSNumber")) {
                        if(nhsNumber.equals("0")) {
                            nhsNumber = param.getValueIdentifier().getValue();
                            //get run mode
                            runMode = getRunMode();
                            //test mode
                            if (runMode != null && runMode.equals(CONFIG_ID_RUN_MODE_TEST)) {
                                originalNHSNumber = nhsNumber;
                                nhsNumber = getMappedTestNHSNumber(nhsNumber);
                            }
                        }
                    } else if (paramName.equals("patientDOB")) {
                            dateOfBirth = param.getValueIdentifier().getValue();
                    } else if (paramName.equals("demographicsOnly")) {
                        if (param.getPart().get(0) != null && param.getPart().get(0).getValueBoolean()) {
                            onlyDemographics = true;
                        }
                    } else if (paramName.equals("activePatientsOnly")) {
                        if (param.getPart().get(0) != null && param.getPart().get(0).getValueBoolean()) {
                            activePatientsOnly = true;
                        }
                    }
                }

                try {
                    json = getFhirBundle(0, nhsNumber, dateOfBirth, onlyDemographics, activePatientsOnly);

                } catch (Exception e) {
                    throw new ResourceNotFoundException("Resource error:" + e);
                }
                return json;
            default:
                // throw exception if called method is not implemented
                break;
        }
        return null;
    }

    /*
     * Get run mode from config table
     */
    private String getRunMode() {
        try {

            jsonTestNHSIdMappings = ConfigManager.getConfigurationAsJson(CONFIG_ID_RUN_MODE);
            runMode = jsonTestNHSIdMappings.get("mode").asText();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return runMode;
    }

    /*
     * Check if the mapping for input NHS number exists in the config table and return the mapped NHS number
     */
    private String getMappedTestNHSNumber(String nhsNumberIn) throws ResourceNotFoundException {
        JsonNode nhsMappingsNodes = jsonTestNHSIdMappings.get("nhs_mappings");
        String mappedNHSNumber = null;
        if (nhsMappingsNodes != null) {
            for (int i = 0; i < nhsMappingsNodes.size(); i++) {
                JsonNode nhsMappingNode = nhsMappingsNodes.get(i);
                String anonymisedNHSNumber = nhsMappingNode.get("anonymised").asText();
                if(nhsNumberIn != null && nhsNumberIn.equals(anonymisedNHSNumber)) {
                    mappedNHSNumber = nhsMappingNode.get("mapped").asText();
                    return mappedNHSNumber;
                }
            }
        }
        if (mappedNHSNumber == null) {
            LOG.error("NHS number " + nhsNumberIn + " passed in is not in the mapping config");
            throw new ResourceNotFoundException("Resource error: NHS number " +
                    "" + nhsNumberIn + " passed in is not in the mapping config");
        }
        return null;
    }

     JDBCDAL getgetFHIRRecordAPIObject(){
        return new JDBCDAL();
    }

    public JSONObject getFhirBundle(Integer id, String nhsNumber, String dateOfBirth) throws Exception {
        return getFhirBundle(id, nhsNumber, dateOfBirth, false, false) ;
    }

    public JSONObject getFhirBundle(Integer id, String nhsNumber, String dateOfBirth, boolean onlyDemographics, boolean activePatientsOnly) throws Exception {
        organizationFhirMap = new HashMap<>();
        encounterFhirMap = new HashMap<>();
        patientCodingMap = new HashMap<>();
        //Practitioner and PractitionerRole Resource
        practitionerAndRoleResource = new HashMap<>();
        episodeOfCareResourceMap= new HashMap<>();
        PatientFull patient = null;
        observationIds = new HashSet<>();

        try (JDBCDAL viewerDAL = new JDBCDAL()) {

            if (id > 0 || !dateOfBirth.equals("0"))
                patient = viewerDAL.getPatientFull(id, nhsNumber, dateOfBirth, activePatientsOnly);
            else
                patient = viewerDAL.getPatientFull(nhsNumber, activePatientsOnly);
            //hide  Demographic details for run mode = test
            if (runMode != null && runMode.equals(CONFIG_ID_RUN_MODE_TEST)) {
              hideDemographicInfo(patient);
            }

            LOG.info("Got Patient");
            if (patient == null) {
                throw new ResourceNotFoundException("Patient resource with id = '" + nhsNumber + "' not found");
            }

            patientResource = Patient.getPatientResource(patient, viewerDAL);

            bundle = new Bundle();
            bundle.setType(Bundle.BundleType.COLLECTION);
            Meta meta = new Meta();
            meta.addProfile("https://fhir.hl7.org.uk/STU3/StructureDefinition/CareConnect-StructuredRecord-Bundle-1");
            bundle.setMeta(meta);

            if (patient.getOrglocation().trim().length() > 0) {
                patientResource.setManagingOrganization(new Reference(getOrganizationFhirObj(Integer.parseInt(patient.getOrglocation()), viewerDAL)));
            }
            if (patient.getPractitionerId() != 0) {
                patientResource.setGeneralPractitioner(Arrays.asList(new Reference(getPractitionerResource(patient.getPractitionerId(),viewerDAL))));
            }
            bundle.addEntry().setResource(patientResource);

            if (onlyDemographics) {
                return parseBundle();
            }

            Long patientId = Long.parseLong(patient.getId());
            Map<Long, String> patientMap;
            List<Long> patientIds = null;
            if (!nhsNumber.equals("0")) {
                patientMap = viewerDAL.getPatientIds(nhsNumber, 0L);
                patientIds = patientMap.keySet().stream()
                        .collect(Collectors.toList());
            } else {
                patientMap = viewerDAL.getPatientIds(nhsNumber, patientId);
                patientIds = Arrays.asList(patientId);
            }
            setCoding(patientMap);

            addFhirAllergiesToBundle(patientIds,viewerDAL);

            LOG.info("Got Allergies");
            // Adding MedicationStatement, MedicationRequest, Medication & MedicationStatementList to bundle
            addFhirMedicationStatementToBundle(patientIds,viewerDAL);

            LOG.info("Got Meds");
            addFhirAppointmentToBundle(patientIds,viewerDAL);

            LOG.info("Got Appts");
            addFhirFamilyMemberHistoryToBundle(patientIds,viewerDAL);

            LOG.info("Got family");
            addFhirConditionsToBundle(patientIds,viewerDAL);

            LOG.info("Got condition");
            addEpisodeOfCareToBundle(patientIds,viewerDAL);

            LOG.info("Got EOC");
            addFhirEncountersToBundle(patientIds,viewerDAL);

            LOG.info("Got encounter");
            addDiagnosticReportToBundle(patientIds,viewerDAL);

            LOG.info("Got diag");
            addProcedureToBundle(patientIds,viewerDAL);

            LOG.info("Got proc");
            addLocationToBundle(patient.getOrglocation(),viewerDAL);

            LOG.info("Got loc");
            addFhirImmunizationsToBundle(patientIds,viewerDAL);

            LOG.info("Got imms");
            addFhirReferralRequestsToBundle(patientIds,viewerDAL);

            LOG.info("Got refs");
            addObservationToBundle(patientIds,viewerDAL);

            LOG.info("Got obs");
            addToBundle("organizations");



            if (!practitionerAndRoleResource.isEmpty()) {
                for (Map.Entry entry : practitionerAndRoleResource.entrySet()) {
                    List<Resource> resourceList = (List) entry.getValue();
                    resourceList.forEach(resource -> bundle.addEntry().setResource(resource));
                }
            }

        }
            return parseBundle();

    }

    private JSONObject parseBundle() throws ParseException {
        FhirContext ctx = FhirContext.forDstu3();
        String encodedBundle = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle);

        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(encodedBundle);
    }
    /*
     * Replace demographic info with xxxx and dob/dod with 09-Sep-9999
     */
    private PatientFull hideDemographicInfo( PatientFull patient) {
        String murphString = "XXXX";

        patient.setFirstname(murphString);
        patient.setLastname(murphString);
        patient.setDob(new Date("09-Sep-9999"));
        patient.setAdd1(murphString);
        patient.setAdd2(murphString);
        patient.setAdd3(murphString);
        patient.setAdd4(murphString);
        patient.setPostcode(murphString);
        patient.setNhsNumber(originalNHSNumber);
        patient.setAdduse(murphString);
        patient.setCity(murphString);
        patient.setDod(new Date("09-Sep-9999"));
        patient.setGender("unknown");

        return patient;

    }

    private org.hl7.fhir.dstu3.model.PractitionerRole getPractitionerRoleResource(Long practitionerId, Long organizationID,JDBCDAL viewerDAL) throws Exception {
        if (practitionerAndRoleResource.get(practitionerId) != null) {
            return (org.hl7.fhir.dstu3.model.PractitionerRole) practitionerAndRoleResource.get(practitionerId).get(1);
        }

        PractitionerFull practitionerResult = viewerDAL.getPractitionerFull(practitionerId);
        org.hl7.fhir.dstu3.model.Practitioner practitionerResource = Practitioner.getPractitionerResource(practitionerResult);

        org.hl7.fhir.dstu3.model.PractitionerRole practitionerRoleResource = PractitionerRole.getPractitionerRoleResource(practitionerResult);
        practitionerRoleResource.setPractitioner(new Reference(practitionerResource));
        practitionerRoleResource.setOrganization(new Reference(getOrganizationFhirObj(organizationID,viewerDAL)));
        practitionerAndRoleResource.put(practitionerId, Arrays.asList(practitionerResource, practitionerRoleResource));
        return practitionerRoleResource;
    }

    private org.hl7.fhir.dstu3.model.Practitioner getPractitionerResource(long practitionerId,JDBCDAL viewerDAL) throws Exception {
        if (!practitionerAndRoleResource.containsKey(practitionerId)) {
            PractitionerFull practitionerResult = viewerDAL.getPractitionerFull(practitionerId);
            org.hl7.fhir.dstu3.model.Practitioner practitionerResource = Practitioner.getPractitionerResource(practitionerResult);

            org.hl7.fhir.dstu3.model.PractitionerRole practitionerRoleResource = PractitionerRole.getPractitionerRoleResource(practitionerResult);
            practitionerRoleResource.setPractitioner(new Reference(practitionerResource));
            practitionerAndRoleResource.put(practitionerId, Arrays.asList(practitionerResource, practitionerRoleResource));
        }
        return (org.hl7.fhir.dstu3.model.Practitioner) practitionerAndRoleResource.get(practitionerId).get(0);
    }

    /*
    This method adds condition Fhir Resources to bundle for given patientid
    author:pp141
    */
    private void addFhirConditionsToBundle(List<Long> patientIds,JDBCDAL viewerDAL) throws Exception {
        List<ConditionFull> conditions = viewerDAL.getConditionFullList(patientIds);
        if (conditions.size() > 0) {
            //create AllergiesList Resource
            org.hl7.fhir.dstu3.model.ListResource fihrConditionListObj = ConditionList.getConditionListResource();
            //referencing patient resource reference here
            fihrConditionListObj.setSubject(new Reference(patientResource));

            for (ConditionFull conditionFull : conditions) {
                String observationId = String.valueOf(conditionFull.getId());
                if(!observationIds.contains(observationId)) {
                    observationIds.add(observationId);
                    org.hl7.fhir.dstu3.model.Condition conditionFhirObj = Condition.getConditionResource(conditionFull, viewerDAL);
                    conditionFhirObj.getMeta().addTag(patientCodingMap.get(conditionFull.getPatientId()));
                    fihrConditionListObj.addEntry().setItem(new Reference(conditionFhirObj));
                    conditionFhirObj.setSubject(new Reference(patientResource));
                    bundle.addEntry().setResource(conditionFhirObj);
                }
            }
            bundle.addEntry().setResource(fihrConditionListObj);

        }
    }

    private void addEpisodeOfCareToBundle(List<Long> patientIds,JDBCDAL viewerDAL) throws Exception {
        List<EpisodeOfCareFull> episodeOfCareFullList = viewerDAL.getEpisodeOfCareFull(patientIds);

        Map<Long, List<EpisodeOfCareFull>> episodeOfCareOrganizationMap = getOrganizationList(episodeOfCareFullList);
        if (!episodeOfCareOrganizationMap.isEmpty()) {
            for (Map.Entry<Long, List<EpisodeOfCareFull>> episodeOfCareList : episodeOfCareOrganizationMap.entrySet()) {
                org.hl7.fhir.dstu3.model.EpisodeOfCare episodeOfCare = EpisodeOfCare.getEpisodeOfCareResource(episodeOfCareList.getValue());
                episodeOfCare.getMeta().addTag(patientCodingMap.get(((episodeOfCareList.getValue()).get(0).getPatientId())));
                episodeOfCare.setPatient(new Reference(patientResource));
                if (episodeOfCareFullList.get(0).getOrganizationId() != 0) {
                    episodeOfCare.setManagingOrganization(new Reference(getOrganizationFhirObj(episodeOfCareFullList.get(0).getOrganizationId(),viewerDAL)));
                }
                if (episodeOfCareFullList.get(0).getPractitionerId() != 0) {
                    episodeOfCare.setCareManager(new Reference(getPractitionerResource(episodeOfCareFullList.get(0).getPractitionerId(),viewerDAL)));
                }
                episodeOfCareResourceMap.put(getEpisodeOfCareResource(episodeOfCareFullList), episodeOfCare);
                bundle.addEntry().setResource(episodeOfCare);
            }
        }
    }

    private String getEpisodeOfCareResource(List<EpisodeOfCareFull> episodeOfCareFullList){
        return episodeOfCareFullList.stream().map(p -> String.valueOf(p.getId()))
                .collect(Collectors.joining(","));

    }

    private void addLocationToBundle(String organizationId,JDBCDAL viewerDAL) throws Exception {
        if (StringUtils.isNotEmpty(organizationId)) {
            LocationFull locationFull = viewerDAL.getLocation(Integer.parseInt(organizationId));
            if(locationFull.getId() != 0) {
                org.hl7.fhir.dstu3.model.Location locationModel = Location.getLocationResource(locationFull);

                locationModel.setManagingOrganization(new Reference(organizationId));
                bundle.addEntry().setResource(locationModel);
            }
        }
    }

    private Map<Long,List<EpisodeOfCareFull>> getOrganizationList(List<EpisodeOfCareFull> episodeOfCareFullList){
        Map<Long,List<EpisodeOfCareFull>> episodeOfCareOrganizationList = new HashMap<>();
        episodeOfCareFullList.forEach(episodeOfCareFull -> {
            if(episodeOfCareOrganizationList.containsKey(episodeOfCareFull.getOrganizationId())){
                episodeOfCareOrganizationList.get(episodeOfCareFull.getOrganizationId()).add(episodeOfCareFull);
            } else {
                episodeOfCareOrganizationList.put(episodeOfCareFull.getOrganizationId(), new ArrayList<>(Arrays.asList(episodeOfCareFull)));
            }
        });
        return episodeOfCareOrganizationList;
    }

    /*
    This method create AllergiesList , Allergies FhirResources and adds to the bundle
    author :pp141
    */
    private void addFhirAllergiesToBundle(List<Long> patientIds ,JDBCDAL viewerDAL) throws Exception {
        List<AllergyFull> allergies = Stream.concat(viewerDAL.getAllergyFullList(patientIds).stream(), viewerDAL.getAllergyFullListFromObservation(patientIds).stream())
                .collect(Collectors.toList());

        if (allergies.size() > 0) {

            //create AllergiesList Resource
            org.hl7.fhir.dstu3.model.ListResource fihrAllergyListObj = AllergyList.getAllergyListResource();
            //referencing patient resource reference here
            fihrAllergyListObj.setSubject(new Reference(patientResource));
            for (AllergyFull allegyFull : allergies) {
                org.hl7.fhir.dstu3.model.AllergyIntolerance allergyFhirObj = AllergyIntolerance.getAllergyIntoleranceResource(allegyFull);
                allergyFhirObj.getMeta().addTag(patientCodingMap.get((allegyFull.getPatientId())));
                Long organizationID = new Long(allegyFull.getOrganizationId());
                allergyFhirObj.setAsserter(new Reference(getPractitionerRoleResource(new Long(allegyFull.getPractitionerId()), organizationID,viewerDAL)));

                bundle.addEntry().setResource(allergyFhirObj);
                fihrAllergyListObj.addEntry().setItem(new Reference(allergyFhirObj));
            }
            bundle.addEntry().setResource(fihrAllergyListObj);

        }
    }

    private void addObservationToBundle(List<Long> patientIds,JDBCDAL viewerDAL) throws Exception {
        // Observation resource
        List<ObservationFull> observationFullList = viewerDAL.getObservationFullList(patientIds);

        if (observationFullList.size() > 0) {
            org.hl7.fhir.dstu3.model.ListResource observationListResource = ObservationList.getObservationResource();
            observationListResource.setSubject(new Reference(patientResource));
            if (!observationFullList.isEmpty()) {
                for (ObservationFull observationFull : observationFullList) {
                    String observationId = String.valueOf(observationFull.getId());
                    if(!observationIds.contains(observationId)) {
                        observationIds.add(observationId);
                        Observation observationFhir = new Observation(observationFull, viewerDAL);
                        org.hl7.fhir.dstu3.model.Observation observationResource = observationFhir.getObservationResource();
                        observationResource.getMeta().addTag(patientCodingMap.get((observationFull.getPatientId())));
                        observationResource.setPerformer(Arrays.asList(new Reference(getPractitionerRoleResource(new Long(observationFull.getPractitionerId()), observationFull.getOrganizationId(), viewerDAL))));
                        observationResource.setSubject(new Reference(patientResource));
                        if (observationFull.getEncounterId() != 0) {
                            observationResource.setContext(new Reference(getEncounterFhirObj(observationFull.getEncounterId(), viewerDAL)));
                        }
                        bundle.addEntry().setResource(observationResource);
                        observationListResource.addEntry().setItem(new Reference(observationResource));
                    }
                }
            }
            bundle.addEntry().setResource(observationListResource);
        }
    }

    private void addDiagnosticReportToBundle(List<Long> patientIds,JDBCDAL viewerDAL) throws Exception {
        // Observation resource
        List<DiagnosticReportFull> diagnosticReportFullList = viewerDAL.getDiagnosticReportFullList(patientIds);

        if (diagnosticReportFullList.size() > 0) {
            org.hl7.fhir.dstu3.model.ListResource observationListResource = ObservationList.getObservationResource();
            observationListResource.setSubject(new Reference(patientResource));
            if (!diagnosticReportFullList.isEmpty()) {
                for (DiagnosticReportFull diagnosticReportFull : diagnosticReportFullList) {
                    String observationId = String.valueOf(diagnosticReportFull.getId());
                    if(!observationIds.contains(observationId)) {
                        observationIds.add(observationId);
                        DiagnosticReport diagnosticFhir = new DiagnosticReport(diagnosticReportFull, viewerDAL);
                        org.hl7.fhir.dstu3.model.DiagnosticReport observationResource = diagnosticFhir.getDiagnosticReport();
                        observationResource.getMeta().addTag(patientCodingMap.get((diagnosticReportFull.getPatientId())));
                        // observationResource.setPerformer(Arrays.asList(new Reference(getPractitionerRoleResource(new Long(observationFull.getPractitionerId()), observationFull.getOrganizationId(),viewerDAL))));
                        observationResource.setSubject(new Reference(patientResource));
                        if (diagnosticReportFull.getEncounterId() != 0) {
                            observationResource.setContext(new Reference(getEncounterFhirObj(diagnosticReportFull.getEncounterId(), viewerDAL)));
                        }
                        bundle.addEntry().setResource(observationResource);
                        observationListResource.addEntry().setItem(new Reference(observationResource));
                    }
                }
            }
            bundle.addEntry().setResource(observationListResource);
        }
    }

    /**
     * Method to add Medication Statement, Medication Request, List(Medication Statement), Medication FHIR resource to bundle
     *
     * @param patientIds
     * @throws Exception
     */
    private void addFhirMedicationStatementToBundle(List<Long> patientIds,JDBCDAL viewerDAL) throws Exception {
        List<MedicationStatementFull> medicationStatementList = null;
        org.hl7.fhir.dstu3.model.MedicationStatement medicationStatementResource = null;
        org.hl7.fhir.dstu3.model.Medication medicationResource = null;

        medicationStatementList = viewerDAL.getMedicationStatementFullList(patientIds);
        if (medicationStatementList != null || medicationStatementList.size() > 0) {
            org.hl7.fhir.dstu3.model.ListResource fhirMedicationStatementList = MedicationStatementList.getMedicationStatementListResource();
            fhirMedicationStatementList.setSubject(new Reference(patientResource));

            for (MedicationStatementFull medicationStatementFull : medicationStatementList) {
                medicationStatementResource = MedicationStatement.getMedicationStatementResource(medicationStatementFull);
                medicationStatementResource.getMeta().addTag(patientCodingMap.get((medicationStatementFull.getPatientId())));
                medicationStatementResource.setSubject(new Reference(patientResource));

                medicationResource = MedicationStatement.getMedicationResource(medicationStatementFull);
                medicationStatementResource.setMedication(new Reference(medicationResource));

                //Medication Request FHIR resource
                List<MedicationOrderFull> medicationRequestList = null;
                org.hl7.fhir.dstu3.model.MedicationRequest medicationRequestResource = null;

                medicationRequestList = viewerDAL.getMedicationOrderFullList(medicationStatementFull.getId(),patientIds );
                if (medicationRequestList != null || medicationRequestList.size() > 0) {

                    List<Reference> primaryMedReqRefList = new ArrayList<>();
                    for (MedicationOrderFull medicationOrderFull : medicationRequestList) {
                        medicationRequestResource = MedicationStatement.getMedicationRequestResource(medicationOrderFull);
                        medicationRequestResource.setSubject(new Reference(patientResource));
                        medicationRequestResource.setMedication(new Reference(medicationResource));
                        medicationRequestResource.setRecorder(new Reference(getPractitionerRoleResource(medicationOrderFull.getPractitionerId(), medicationOrderFull.getOrgId(),viewerDAL)));

                        medicationRequestResource.addIdentifier()
                                .setValue(String.valueOf(medicationOrderFull.getId()))
                                .setSystem(ResourceConstants.SYSTEM_ID);

                        if (medicationRequestList.get(0).equals(medicationOrderFull)) {
                            primaryMedReqRefList.add(new Reference(medicationRequestResource));
                            medicationStatementResource.setBasedOn(primaryMedReqRefList);
                        } else {
                            medicationRequestResource.setBasedOn(primaryMedReqRefList);
                        }
                        bundle.addEntry().setResource(medicationRequestResource);
                    }
                }
                //Medication Request FHIR resource

                bundle.addEntry().setResource(medicationStatementResource);
                bundle.addEntry().setResource(medicationResource);
                fhirMedicationStatementList.addEntry().setItem(new Reference(medicationStatementResource));
            }
            bundle.addEntry().setResource(fhirMedicationStatementList);
        }
    }

    /*
       This method creates new organization resource for given organization id
       and add it to global organizationMap , if it is not available in global map
       and returns the organization resource from global organizationMap
       author :pp141
     */
    private Resource getOrganizationFhirObj(long organizationID,JDBCDAL viewerDAL) throws Exception {
        if (!organizationFhirMap.containsKey(organizationID)) {
            OrganizationFull patient_organization = viewerDAL.getOrganizationFull(organizationID);
            organizationFhirMap.put(organizationID, Organization.getOrganizationResource(patient_organization));
        }
        return organizationFhirMap.get(organizationID);
    }

    /*
    This method will write global resources from maps to bundle
    author:pp141
     */
    private void addToBundle(String bundlename) {
        if (bundlename.equalsIgnoreCase("organizations")) {
            Iterator iter = organizationFhirMap.keySet().iterator();
            while (iter.hasNext()) {
                bundle.addEntry().setResource((org.hl7.fhir.dstu3.model.Resource) organizationFhirMap.get(iter.next()));
            }
        }
    }

    /*
    Create Encounters FhirResources and adds to the bundle
    author :pp141
    */
    private void addFhirEncountersToBundle(List<Long> patientIds,JDBCDAL viewerDAL) throws Exception {
        List<EncounterFull> encounterFullList = viewerDAL.getEncounterFullList(patientIds, 0L, true);

        if (encounterFullList.size() > 0) {

            for (EncounterFull encounterFull : encounterFullList) {
                org.hl7.fhir.dstu3.model.Encounter encounterObj = Encounter.getEncounterResource(encounterFull);
                encounterObj.getMeta().addTag(patientCodingMap.get((encounterFull.getPatientId())));
                encounterObj.setSubject(new Reference(patientResource));
                encounterObj.setEpisodeOfCare(getEpisodeOfCareReference(encounterFull.getEpisode_of_care_id()));
                if(encounterFull.getPractitionerId()!=-1)
                {
                    org.hl7.fhir.dstu3.model.Encounter.EncounterParticipantComponent epc=new org.hl7.fhir.dstu3.model.Encounter.EncounterParticipantComponent();
                    List<CodeableConcept> coding=new ArrayList<>();


                    CodeableConcept code = new CodeableConcept();
                    code.addCoding()
                            .setCode("PPRF")
                            .setDisplay("primary performer")
                            .setSystem("http://hl7.org/fhir/ValueSet/encounter-participant-type");
                    coding.add(code);
                   epc.setType(coding);
                   epc.setIndividual(new Reference(getPractitionerRoleResource(encounterFull.getPractitionerId(), encounterFull.getOrganizationId(),viewerDAL)));
                   encounterObj.getParticipant().add(epc);

                }


                Long encounterID=new Long(encounterFull.getEncounterid());
                if (!encounterFhirMap.containsKey(encounterID)) {
                    encounterFhirMap.put(encounterID, encounterObj);
                    bundle.addEntry().setResource(encounterObj);
                }
                }
            }

        }

    private List<Reference> getEpisodeOfCareReference(String episode_of_care_id) {
        List<Reference> references = new ArrayList<>();
        for (Map.Entry<String, Resource> entry : episodeOfCareResourceMap.entrySet()) {
            if (Arrays.asList(entry.getKey().split(",")).contains(episode_of_care_id)) {
                references.add(new Reference(entry.getValue()));
            }
        }
        return (references.isEmpty()) ?  null : references;
    }


    /*
   Create Encounters FhirResource and adds to the bundle
   author :pp141
   */
    private Resource getEncounterFhirObj(Long encounterID,JDBCDAL viewerDAL) throws Exception {

        if (!encounterFhirMap.containsKey(encounterID)) {
            List<EncounterFull> encounterFullLis = viewerDAL.getEncounterFullList(Collections.emptyList(), encounterID, false);
            EncounterFull encounterFull = encounterFullLis.get(0);
            org.hl7.fhir.dstu3.model.Encounter encounterObj = Encounter.getEncounterResource(encounterFull);
            encounterObj.setSubject(new Reference(patientResource));
            encounterFhirMap.put(encounterID, encounterObj);
            bundle.addEntry().setResource(encounterObj);
        }
            return encounterFhirMap.get(encounterID);
    }

    private void addProcedureToBundle(List<Long> patientIds,JDBCDAL viewerDAL) throws Exception {
        List<ProcedureFull> procedureFullList= viewerDAL.getProcedureFull(patientIds);

        if (!procedureFullList.isEmpty()) {

            for (ProcedureFull procedureFull : procedureFullList) {
                if(!observationIds.contains(procedureFull.getId())) {
                    observationIds.add(procedureFull.getId());
                    org.hl7.fhir.dstu3.model.Procedure procedureResource = Procedure.getProcedureResource(procedureFull);
                    procedureResource.getMeta().addTag(patientCodingMap.get((procedureFull.getPatientId())));
                    procedureResource.setPerformer(Arrays.asList(new org.hl7.fhir.dstu3.model.Procedure.ProcedurePerformerComponent(new Reference
                            (getPractitionerRoleResource(new Long(procedureFull.getPractitionerId()), procedureFull.getOrganizationId(), viewerDAL)))));
                    procedureResource.setSubject(new Reference(patientResource));
                    bundle.addEntry().setResource(procedureResource);
                }
            }
        }
    }

    /*
   Create Encounters FhirResources and adds to the bundle
   author :pp141
   */
    private void addFhirImmunizationsToBundle(List<Long> patientIds,JDBCDAL viewerDAL) throws Exception {
        List<ImmunizationFull> immunizationfullList= viewerDAL.getImmunizationsFullList(patientIds);

        if (immunizationfullList.size() > 0) {

            for (ImmunizationFull immunizationFull : immunizationfullList) {
                String observationId = String.valueOf(immunizationFull.getId());
                if (!observationIds.contains(observationId)) {
                    observationIds.add(observationId);
                    org.hl7.fhir.dstu3.model.Immunization immunizationObj = Immunization.getImmunizationResource(immunizationFull);
                    immunizationObj.getMeta().addTag(patientCodingMap.get((immunizationFull.getPatientId())));
                    immunizationObj.setPatient(new Reference(patientResource));
                    if (immunizationFull.getEncounterID().trim().length() > 0)
                        immunizationObj.setEncounter(new Reference(getEncounterFhirObj(Long.parseLong(immunizationFull.getEncounterID()), viewerDAL)));
                    if (immunizationFull.getPractitionerID().trim().length() > 0)
                        immunizationObj.addPractitioner().setActor(new Reference(getPractitionerResource(Integer.parseInt(immunizationFull.getPractitionerID()), viewerDAL)));
                    bundle.addEntry().setResource(immunizationObj);
                }
                }
            }
        }

    /**
     * Method to add Appointment FHIR resource to bundle
     *
     * @param patientIds
     * @throws Exception
     */
    private void addFhirAppointmentToBundle(List<Long> patientIds,JDBCDAL viewerDAL) throws Exception {
        List<AppointmentFull> appointmentList = null;
        org.hl7.fhir.dstu3.model.Appointment appointmentResource = null;
        org.hl7.fhir.dstu3.model.Slot slotResource = null;
        org.hl7.fhir.dstu3.model.Schedule scheduleResource = null;

        appointmentList = viewerDAL.getAppointmentFullList(patientIds);
        if (appointmentList != null || appointmentList.size() > 0) {
            Appointment fhirAppointment = new Appointment();
            List<Reference> slotList = new ArrayList<Reference>();
            Map<String, Schedule> scheduleIds = new HashMap<>();

            for (AppointmentFull appointmentFull : appointmentList) {
                        appointmentResource = fhirAppointment.getAppointmentResource(appointmentFull);
                        appointmentResource.getMeta().addTag(patientCodingMap.get((appointmentFull.getPatientId())));
                        appointmentResource.addParticipant().setActor(new Reference(patientResource));

                        if(!scheduleIds.containsKey(appointmentFull.getScheduleId())) {
                            List<Reference> actorList = new ArrayList<Reference>();
                            scheduleResource = fhirAppointment.getScheduleResource(appointmentFull);
                            Reference organizationResource = new Reference(getOrganizationFhirObj(appointmentFull.getOrgId(),viewerDAL));
                            organizationResource.setDisplay("Organization");
                            actorList.add(organizationResource);
                            if (appointmentFull.getPractitionerId()!=0) {
                                Reference practitionerRoleReference = new Reference(getPractitionerRoleResource(appointmentFull.getPractitionerId(), appointmentFull.getOrgId(),viewerDAL));
                                practitionerRoleReference.setDisplay("PractitionerRole");
                                actorList.add(practitionerRoleReference);
                                Reference practitionerReference = new Reference(getPractitionerResource(appointmentFull.getPractitionerId(),viewerDAL));
                                practitionerReference.setDisplay("Practitioner");
                                actorList.add(practitionerReference);
                            }
                            scheduleResource.setActor(actorList);
                            bundle.addEntry().setResource(scheduleResource);
                        } else {
                            scheduleResource = scheduleIds.get(scheduleResource);
                        }

                        bundle.addEntry().setResource(appointmentResource);
                        slotList = new ArrayList<>();
                        slotResource = fhirAppointment.getSlotResource(appointmentFull);
                        slotResource.setSchedule(new Reference(scheduleResource));
                        slotList.add(new Reference(slotResource));
                        appointmentResource.setSlot(slotList);
                        bundle.addEntry().setResource(slotResource);

            }
        }
    }

    /**
     * Method to add FamilyMemberHistory FHIR resource to bundle
     *
     * @param patientIds
     * @throws Exception
     */
    private void addFhirFamilyMemberHistoryToBundle(List<Long> patientIds,JDBCDAL viewerDAL) throws Exception {
        List<FamilyMemberHistoryFull> familyMemberHistoryList = null;
        org.hl7.fhir.dstu3.model.FamilyMemberHistory familyMemberHistoryResource = null;

        familyMemberHistoryList = viewerDAL.getFamilyMemberHistoryFullList(patientIds);
        if (familyMemberHistoryList != null || familyMemberHistoryList.size() > 0) {
            FamilyMemberHistory familyMemberHistory = new FamilyMemberHistory();

            for (FamilyMemberHistoryFull familyMemberHistoryFull : familyMemberHistoryList) {
                String observationId = String.valueOf(familyMemberHistoryFull.getId());
                if(!observationIds.contains(observationId)) {
                    observationIds.add(observationId);
                    familyMemberHistoryResource = familyMemberHistory.getFamilyMemberHistoryResource(familyMemberHistoryFull);
                    familyMemberHistoryResource.getMeta().addTag(patientCodingMap.get((familyMemberHistoryFull.getPatientId())));
                    familyMemberHistoryResource.setPatient(new Reference(patientResource));

                    bundle.addEntry().setResource(familyMemberHistoryResource);
                }
            }
        }
    }

    /*
  Create Encounters FhirResources and adds to the bundle
  author :pp141
  */
    private void addFhirReferralRequestsToBundle(List<Long> patientIds,JDBCDAL viewerDAL) throws Exception {
        List<ReferralRequestFull> referralRequestFullList= viewerDAL.getReferralRequestFullList(patientIds);

        if (referralRequestFullList.size() > 0) {

            for (ReferralRequestFull referralRequestFull : referralRequestFullList) {
                org.hl7.fhir.dstu3.model.ReferralRequest referralRequest = ReferralRequest.getReferralRequestResource(referralRequestFull);
                referralRequest.getMeta().addTag(patientCodingMap.get((referralRequestFull.getPatientId())));

                referralRequest.setSubject(new Reference(patientResource));
                referralRequest.addIdentifier()
                        .setValue(String.valueOf(referralRequestFull.getId()))
                        .setSystem(ResourceConstants.SYSTEM_ID);
                if(referralRequestFull.getRecipientOrganizationId()!=null) {
                    List<Reference> recipients=new ArrayList<>();
                    recipients.add(new Reference(getOrganizationFhirObj(Integer.parseInt(referralRequestFull.getRecipientOrganizationId()),viewerDAL)));
                    referralRequest.setRecipient(recipients);
                }
                    if(referralRequestFull.getPractitionerId()!=null)
                        referralRequest.setRequester(new org.hl7.fhir.dstu3.model.ReferralRequest.ReferralRequestRequesterComponent(new Reference(getPractitionerResource( Integer.parseInt(referralRequestFull.getPractitionerId()),viewerDAL))));
                    bundle.addEntry().setResource(referralRequest);
            }
        }
    }


    private void setCoding(Map<Long, String> patientMap) {
        patientMap.entrySet().stream().forEach(e -> {
            Long patientId = e.getKey();
            if (patientMap.containsKey(patientId)) {
                String[] values = patientMap.get(patientId).split("#");
                Coding coding = new Coding();
                coding.setCode(values[0]);
                coding.setDisplay(values[1]);
                coding.setSystem(ResourceConstants.ORGANIZATION_CODE);
                patientCodingMap.put(patientId, coding);
            }
        });
    }

}
