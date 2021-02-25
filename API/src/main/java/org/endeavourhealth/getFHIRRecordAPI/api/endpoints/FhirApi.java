package org.endeavourhealth.getFHIRRecordAPI.api.endpoints;

import ca.uhn.fhir.context.FhirContext;
import com.fasterxml.jackson.databind.JsonNode;
import models.Parameter;
import models.Params;
import models.Request;
import models.ResourceNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.endeavourhealth.common.config.ConfigManager;
import org.endeavourhealth.core.database.dal.usermanager.caching.ProjectCache;
import org.endeavourhealth.core.database.dal.usermanager.caching.UserCache;
import org.endeavourhealth.core.database.rdbms.datasharingmanager.models.ProjectEntity;
import org.endeavourhealth.core.database.rdbms.usermanager.models.UserProjectEntity;
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

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class FhirApi {
    private static final Logger LOG = LoggerFactory.getLogger(FhirApi.class);
    private static final String CONFIG_ID_RUN_MODE = "run_mode";
    private static final String CONFIG_ID_RUN_MODE_TEST = "test";

    HashMap<Long, Resource> organizationFhirMap;
    HashMap<Long, Resource> encounterFhirMap;
    HashMap<String, Integer> vitalSignsCounter = new HashMap<>();
    Map<Long, List<Resource>> practitionerAndRoleResource;
    Map<Long, Coding> patientCodingMap;
    Map<String, Resource> episodeOfCareResourceMap;
    JsonNode jsonTestNHSIdMappings;
    String originalNHSNumber = null;
    String runMode = null;
    Boolean useDSM = false;
    Set<String> observationIds;
    Set<String> pathAndRadObservationIds;
    String morphString = "XXXX";
    List<String> validOrgs = new ArrayList<>();
    String configName = null;
    Date baselineDate = null;

    Bundle bundle;
    org.hl7.fhir.dstu3.model.Patient patientResource;
    public JSONObject handleRequest(Request request, String userId) throws ResourceNotFoundException {
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
                boolean activePatientsOnly = true;

                //get run mode
                runMode = getRunMode();
                useDSM = getDSMMode();

                if (useDSM) {
                    try {
                        checkUserAccessToOrganisations(userId);
                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                    }
                } else {
                    // if running locally you might not have DSM fully set up so just take the config name to use from the runMode config entry.
                    // Note, this should never be used in a live environment
                    getDevConfigName();
                }

                for (Parameter param : parameters) {
                    String paramName = param.getName();
                    if (paramName.equals("patientNHSNumber")) {
                        if(nhsNumber.equals("0")) {
                            nhsNumber = param.getValueIdentifier().getValue();
                            //test mode
                            if (runMode != null && runMode.equals(CONFIG_ID_RUN_MODE_TEST)) {
                                originalNHSNumber = nhsNumber;
                                nhsNumber = getMappedTestNHSNumber(nhsNumber);
                            }
                        }
                    } else if (paramName.equals("patientDOB")) {
                        if (runMode != null && !runMode.equals(CONFIG_ID_RUN_MODE_TEST)) {
                            dateOfBirth = param.getValueIdentifier().getValue();
                        }
                    } else if (paramName.equals("demographicsOnly")) {
                        if (param.getPart().get(0) != null && param.getPart().get(0).getValueBoolean()) {
                            onlyDemographics = true;
                        }
                    } else if (paramName.equals("includeInactivePatients")) {
                        if (param.getPart().get(0) != null && param.getPart().get(0).getValueBoolean()) {
                            activePatientsOnly = false;
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

    private Boolean getDSMMode() {
        try {
            useDSM = false;
            useDSM = jsonTestNHSIdMappings.get("useDSM").asBoolean();
            LOG.info("using DSM = " + useDSM);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return useDSM;
    }

    private void getDevConfigName() {
        try {

            configName = jsonTestNHSIdMappings.get("devConfigName").asText();
            LOG.info("using dev config = " + configName);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    private void checkUserAccessToOrganisations(String userId) throws Exception {

        if (userId == null || !userId.equals("")) {

            List<UserProjectEntity> userProjects = UserCache.getUserProjectForUserId(userId);

            if (userProjects.size() < 1) {
                LOG.info("User is not assigned to a project...unable to find organisations");
                return;
            } else if (userProjects.size() > 1) {
                LOG.info("User is assigned to multiple projects...unable to find organisations");
            }

            String projectId = userProjects.get(0).getProjectId();

            List<String> orgList = ProjectCache.getAllPublishersForValidProject(projectId, true);

            ProjectEntity project = ProjectCache.getProjectDetails(projectId);

            if (project != null) {
                configName = project.getConfigName();
            }

            validOrgs = orgList;

            for (String org : orgList) {
                LOG.info(org);
            }

        }
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

    private void setBaselineDateForFilters() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        baselineDate = cal.getTime();
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
        pathAndRadObservationIds = new HashSet<>();

        // set the baseline date for filters to use
        setBaselineDateForFilters();
        vitalSignsCounter.clear();

        try (JDBCDAL viewerDAL = new JDBCDAL()) {

            viewerDAL.setValidOrgs(validOrgs);
            viewerDAL.setSubscriberConnection(configName);

            if (id > 0 || !dateOfBirth.equals("0"))
                patient = viewerDAL.getPatientFull(id, nhsNumber, dateOfBirth, activePatientsOnly, useDSM);
            else
                patient = viewerDAL.getPatientFull(nhsNumber, activePatientsOnly, useDSM);

            LOG.info("Got Patient");
            if (patient == null) {
                LOG.info("Patient not found");
                throw new ResourceNotFoundException("Patient resource with id = '" + nhsNumber + "' not found");
            }

            List<TelecomFull> telecomFullList = viewerDAL.getTelecomFull(Long.parseLong(patient.getId()));
            patient.setTelecomFullList(telecomFullList);

            //hide  Demographic details for run mode = test
            if (runMode != null && runMode.equals(CONFIG_ID_RUN_MODE_TEST)) {
              hideDemographicInfo(patient);
            }

            patientResource = Patient.getPatientResource(patient, viewerDAL);

            bundle = new Bundle();
            bundle.setType(Bundle.BundleType.COLLECTION);
            Meta meta = new Meta();
            meta.addProfile("https://fhir.hl7.org.uk/STU3/StructureDefinition/CareConnect-StructuredRecord-Bundle-1");
            bundle.setMeta(meta);

            if (patient.getOrglocation().trim().length() > 0) {
                patientResource.setManagingOrganization(new Reference(getOrganizationFhirObj(Long.parseLong(patient.getOrglocation()), viewerDAL)));
            }
            if (patient.getPractitionerId() != 0) {
                org.hl7.fhir.dstu3.model.Practitioner practitioner = getPractitionerResource(patient.getPractitionerId(),viewerDAL);
                if (practitioner != null) {
                    patientResource.setGeneralPractitioner(Arrays.asList(new Reference(practitioner)));
                }
            }
            bundle.addEntry().setResource(patientResource);

            if (onlyDemographics) {
                return parseBundle();
            }

            Long patientId = Long.parseLong(patient.getId());
            Map<Long, String> patientMap;
            List<Long> patientIds = null;
            if (!nhsNumber.equals("0")) {
                patientMap = viewerDAL.getPatientIds(nhsNumber, 0L, useDSM);
                patientIds = patientMap.keySet().stream()
                        .collect(Collectors.toList());
            } else {
                patientMap = viewerDAL.getPatientIds(nhsNumber, patientId, useDSM);
                patientIds = Arrays.asList(patientId);
            }
            setCoding(patientMap);

            // get pathology and radiology observations so we can filter these out
            pathAndRadObservationIds = viewerDAL.getPathologyAndRadiologyList(patientIds);

            addFhirConditionsToBundle(patientIds,viewerDAL);

            LOG.info("Got condition");
            addFhirAllergiesToBundle(patientIds,viewerDAL);

            LOG.info("Got Allergies");
            // Adding MedicationStatement, MedicationRequest, Medication & MedicationStatementList to bundle
            addFhirMedicationStatementToBundle(patientIds,viewerDAL);

            LOG.info("Got Meds");
/*

            addFhirAppointmentToBundle(patientIds,viewerDAL);
            LOG.info("Got Appts");
*/

            addFhirFamilyMemberHistoryToBundle(patientIds,viewerDAL);

            LOG.info("Got family");
            addEpisodeOfCareToBundle(patientIds,viewerDAL);

            LOG.info("Got EOC");
            addFhirEncountersToBundle(patientIds,viewerDAL);

            LOG.info("Got encounter");
            /*
            addDiagnosticReportToBundle(patientIds,viewerDAL);
            LOG.info("Got diag");
            */

            /*
            addProcedureToBundle(patientIds,viewerDAL);
            LOG.info("Got proc");
            */
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

        patient.setFirstname(morphString);
        patient.setLastname(morphString);
        patient.setDob(new Date("09-Sep-9999"));
        patient.setAdd1(morphString);
        patient.setAdd2(morphString);
        patient.setAdd3(morphString);
        patient.setAdd4(morphString);
        patient.setPostcode(morphString);
        patient.setNhsNumber(originalNHSNumber);
        patient.setAdduse(morphString);
        patient.setCity(morphString);
        patient.setDod(new Date("09-Sep-9999"));
        patient.setGender("Unknown");

        patient.setEthnicDescription(morphString);

        if(patient.getTelecomFullList() != null && patient.getTelecomFullList().size() > 0) {
            morphContactDetail(patient);
        }

        return patient;

    }
    /*
     * Morph contact detail
     */
    private PatientFull morphContactDetail( PatientFull patient) {
        for(TelecomFull telecomFull : patient.getTelecomFullList()) {
            telecomFull.setValue(morphString);
        }
        return patient;
    }

    private org.hl7.fhir.dstu3.model.PractitionerRole getPractitionerRoleResource(Long practitionerId, Long organizationID,JDBCDAL viewerDAL) throws Exception {
        if (practitionerAndRoleResource.get(practitionerId) != null) {
            return (org.hl7.fhir.dstu3.model.PractitionerRole) practitionerAndRoleResource.get(practitionerId).get(1);
        }

        PractitionerFull practitionerResult = viewerDAL.getPractitionerFull(practitionerId);
        if (practitionerResult == null) {
            return null;
        }
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

            if (practitionerResource == null) {
                return null;
            }

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
                    org.hl7.fhir.dstu3.model.Practitioner practitioner = getPractitionerResource(episodeOfCareFullList.get(0).getPractitionerId(),viewerDAL);
                    if (practitioner != null) {
                        episodeOfCare.setCareManager(new Reference(practitioner));
                    }
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
            LocationFull locationFull = viewerDAL.getLocation(Long.parseLong(organizationId));
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
        // Only return allergies from the allergy intolerance table and not from observation
        /*List<AllergyFull> allergies = Stream.concat(viewerDAL.getAllergyFullList(patientIds).stream(), viewerDAL.getAllergyFullListFromObservation(patientIds).stream())
                .collect(Collectors.toList());*/

        List<AllergyFull> allergies = viewerDAL.getAllergyFullList(patientIds);

        if (allergies.size() > 0) {

            //create AllergiesList Resource
            org.hl7.fhir.dstu3.model.ListResource fihrAllergyListObj = AllergyList.getAllergyListResource();
            //referencing patient resource reference here
            fihrAllergyListObj.setSubject(new Reference(patientResource));
            for (AllergyFull allegyFull : allergies) {
                org.hl7.fhir.dstu3.model.AllergyIntolerance allergyFhirObj = AllergyIntolerance.getAllergyIntoleranceResource(allegyFull);
                allergyFhirObj.getMeta().addTag(patientCodingMap.get((allegyFull.getPatientId())));
                Long organizationID = new Long(allegyFull.getOrganizationId());

                org.hl7.fhir.dstu3.model.PractitionerRole practitionerRole = getPractitionerRoleResource(allegyFull.getPractitionerId(), organizationID, viewerDAL);
                if (practitionerRole != null) {
                    allergyFhirObj.setAsserter(new Reference(practitionerRole));
                }


                bundle.addEntry().setResource(allergyFhirObj);
                fihrAllergyListObj.addEntry().setItem(new Reference(allergyFhirObj));
            }
            bundle.addEntry().setResource(fihrAllergyListObj);

        }
    }
    private boolean filterVitalSigns(ObservationFull observationFull) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date observationDate = format.parse(observationFull.getDate());

        String code = observationFull.getCode();
        String category = observationFull.getCategory();

        if (!category.equals("vital-signs")) {
            return true;
        }

        Integer counter = vitalSignsCounter.get(code);
        if (counter == null) {
            counter = 0;
        }

        // if the observation is within the last year or if we have less than 3 then include it
        if (observationDate.after(baselineDate) || counter < 3) {
            vitalSignsCounter.put(code, ++counter);
            return true;
        }

        return false;
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
                    if(!observationIds.contains(observationId) && !pathAndRadObservationIds.contains(observationId)) {
                        observationIds.add(observationId);

                        // add filtering to the vital signs
                        if (!filterVitalSigns(observationFull)) {
                            continue;
                        }
                        Observation observationFhir = new Observation(observationFull, viewerDAL);
                        org.hl7.fhir.dstu3.model.Observation observationResource = observationFhir.getObservationResource();
                        observationResource.getMeta().addTag(patientCodingMap.get((observationFull.getPatientId())));

                        org.hl7.fhir.dstu3.model.PractitionerRole practitionerRole = getPractitionerRoleResource(observationFull.getPractitionerId(), observationFull.getOrganizationId(), viewerDAL);
                        if (practitionerRole != null) {
                            observationResource.setPerformer(Arrays.asList(new Reference(practitionerRole)));
                        }
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
                    if(!observationIds.contains(observationId) && !pathAndRadObservationIds.contains(observationId)) {
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

                if (medicationStatementFull.getPractitionerId() != 0) {
                    org.hl7.fhir.dstu3.model.PractitionerRole practitionerRole = getPractitionerRoleResource(medicationStatementFull.getPractitionerId(), medicationStatementFull.getOrganizationId(), viewerDAL);
                    if (practitionerRole != null) {
                        medicationStatementResource.setInformationSource(new Reference(practitionerRole));
                    }
                }

                medicationResource = MedicationStatement.getMedicationResource(medicationStatementFull);
                medicationStatementResource.setMedication(new Reference(medicationResource));

                //Medication Request FHIR resource
                /*  Remove medication issues from message due to performance issues at NWL
                List<MedicationOrderFull> medicationRequestList = null;
                org.hl7.fhir.dstu3.model.MedicationRequest medicationRequestResource = null;

                medicationRequestList = viewerDAL.getMedicationOrderFullList(medicationStatementFull.getId(),patientIds );
                if (medicationRequestList != null || medicationRequestList.size() > 0) {

                    List<Reference> primaryMedReqRefList = new ArrayList<>();
                    for (MedicationOrderFull medicationOrderFull : medicationRequestList) {
                        medicationRequestResource = MedicationStatement.getMedicationRequestResource(medicationOrderFull);
                        medicationRequestResource.setSubject(new Reference(patientResource));
                        medicationRequestResource.setMedication(new Reference(medicationResource));


                        org.hl7.fhir.dstu3.model.PractitionerRole practitionerRole = getPractitionerRoleResource(medicationOrderFull.getPractitionerId(), medicationOrderFull.getOrgId(), viewerDAL);
                        if (practitionerRole != null) {
                            medicationRequestResource.setRecorder(new Reference(practitionerRole));
                        }
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
                */
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

                    org.hl7.fhir.dstu3.model.PractitionerRole practitionerRole = getPractitionerRoleResource(encounterFull.getPractitionerId(), encounterFull.getOrganizationId(), viewerDAL);

                    if (practitionerRole != null) {
                        epc.setIndividual(new Reference(practitionerRole));
                    }

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
            if (encounterFullLis.size() < 1) {
                return null;
            }
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
                if(!observationIds.contains(procedureFull.getId()) && !pathAndRadObservationIds.contains(procedureFull.getId())) {
                    observationIds.add(procedureFull.getId());
                    org.hl7.fhir.dstu3.model.Procedure procedureResource = Procedure.getProcedureResource(procedureFull);
                    procedureResource.getMeta().addTag(patientCodingMap.get((procedureFull.getPatientId())));

                    org.hl7.fhir.dstu3.model.PractitionerRole practitionerRole = getPractitionerRoleResource(procedureFull.getPractitionerId(), procedureFull.getOrganizationId(), viewerDAL);
                    if (practitionerRole != null) {
                        procedureResource.setPerformer(Arrays.asList(new org.hl7.fhir.dstu3.model.Procedure.ProcedurePerformerComponent(new Reference(practitionerRole))));
                    }
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
                if (!observationIds.contains(observationId) && !pathAndRadObservationIds.contains(observationId)) {
                    observationIds.add(observationId);
                    org.hl7.fhir.dstu3.model.Immunization immunizationObj = Immunization.getImmunizationResource(immunizationFull);
                    immunizationObj.getMeta().addTag(patientCodingMap.get((immunizationFull.getPatientId())));
                    immunizationObj.setPatient(new Reference(patientResource));
                    if (immunizationFull.getEncounterID().trim().length() > 0)
                        immunizationObj.setEncounter(new Reference(getEncounterFhirObj(Long.parseLong(immunizationFull.getEncounterID()), viewerDAL)));
                    if (immunizationFull.getPractitionerID().trim().length() > 0) {
                        org.hl7.fhir.dstu3.model.Practitioner practitioner = getPractitionerResource(Long.parseLong(immunizationFull.getPractitionerID()), viewerDAL);
                        if (practitioner != null) {
                            immunizationObj.addPractitioner().setActor(new Reference(practitioner));
                        }
                    }
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


                                org.hl7.fhir.dstu3.model.PractitionerRole practitionerRole = getPractitionerRoleResource(appointmentFull.getPractitionerId(), appointmentFull.getOrgId(),viewerDAL);
                                if (practitionerRole != null) {
                                    Reference practitionerRoleReference = new Reference(practitionerRole);
                                    practitionerRoleReference.setDisplay("PractitionerRole");
                                    actorList.add(practitionerRoleReference);
                                }

                                org.hl7.fhir.dstu3.model.Practitioner practitioner = getPractitionerResource(appointmentFull.getPractitionerId(),viewerDAL);
                                if (practitioner != null) {
                                    Reference practitionerReference = new Reference(practitioner);
                                    practitionerReference.setDisplay("Practitioner");
                                    actorList.add(practitionerReference);
                                }
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
                if(!observationIds.contains(observationId) && !pathAndRadObservationIds.contains(observationId)) {
                    observationIds.add(observationId);
                    familyMemberHistoryResource = familyMemberHistory.getFamilyMemberHistoryResource(familyMemberHistoryFull);
                    org.hl7.fhir.dstu3.model.Practitioner practitioner = getPractitionerResource(familyMemberHistoryFull.getPractitionerId(),viewerDAL);
                    Extension ext1 = new Extension();
                    ext1.setUrl("https://fhir.hl7.org.uk/STU3/StructureDefinition/Extension-CareConnect-Recorder-1");
                    ext1.setValue(new Reference(practitioner));
                    familyMemberHistoryResource.addExtension(ext1);
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
                    recipients.add(new Reference(getOrganizationFhirObj(Long.parseLong(referralRequestFull.getRecipientOrganizationId()),viewerDAL)));
                    referralRequest.setRecipient(recipients);
                }

                if(referralRequestFull.getPractitionerId()!=null) {

                    org.hl7.fhir.dstu3.model.Practitioner practitioner = getPractitionerResource( Long.parseLong(referralRequestFull.getPractitionerId()),viewerDAL);
                    if (practitioner != null) {
                        referralRequest.setRequester(new org.hl7.fhir.dstu3.model.ReferralRequest.ReferralRequestRequesterComponent(new Reference(practitioner)));
                    }
                }

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
