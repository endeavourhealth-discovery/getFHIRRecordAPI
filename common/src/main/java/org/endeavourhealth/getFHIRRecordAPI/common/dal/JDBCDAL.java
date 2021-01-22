package org.endeavourhealth.getFHIRRecordAPI.common.dal;

import org.apache.commons.lang3.StringUtils;
import org.endeavourhealth.getFHIRRecordAPI.common.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JDBCDAL extends BaseJDBCDAL {
    private static final Logger LOG = LoggerFactory.getLogger(JDBCDAL.class);


    public PatientFull getPatientFull(Integer id, String nhsNumber, String dateOfBirth, boolean includeOnlyActivePatient) throws Exception {
        PatientFull result = null;

        String sql = "SELECT p.id,"+
                "coalesce(p.organization_id,'') as orglocation,"+
                "coalesce(o.name,'') as orgname,"+
                "coalesce(p.date_of_birth,'') as dob,"+
                "p.date_of_death as dod,"+
                "coalesce(c.name,'') as gender,"+
                "coalesce(p.nhs_number,'') as nhsNumber,"+
                "coalesce(p.last_name,'') as lastname,"+
                "coalesce(p.first_names,'') as firstname,"+
                "coalesce(p.title,'') as title,"+
                "coalesce(a.address_line_1,'') as add1,"+
                "coalesce(a.address_line_2,'') as add2,"+
                "coalesce(a.address_line_3,'') as add3,"+
                "coalesce(a.address_line_4,'') as add4,"+
                "coalesce(a.city,'') as city,"+
                "coalesce(e.usual_gp_practitioner_id,'') as practitionerId,"+
                "coalesce(a.postcode,'') as postcode," +
                "coalesce(c3.description, '') as ethnicDescription," +
                "coalesce(c2.description, '') as registrationStatusValue," +
                "coalesce(e.registration_type_concept_id,'') as registrationType,"+
                "e.date_registered_end as registeredEndDate,"+
                "coalesce(e.date_registered,'') as startdate,"+
                "'HOME' as adduse,"+
                "'' as otheraddresses "+
                "FROM patient p " +
                "join organization o on o.id = p.organization_id " +
                "join patient_address a on a.id = p.current_address_id " +
                "join concept c on c.dbid = p.gender_concept_id " +
                "join episode_of_care e on e.patient_id = p.id "+
                "join concept c2 on c2.dbid = e.registration_type_concept_id "+
                "left join concept c3 on c3.dbid = p.ethnic_code_concept_id "+
                "where "+
                " (p.id = ? or (p.nhs_number = ? and p.date_of_birth = ?))";


        if (includeOnlyActivePatient){
            sql.concat(" and c2.code = 'R'  and p.date_of_death IS NULL and e.date_registered <= now() and (e.date_registered_end > now() or e.date_registered_end IS NULL)");
        }

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.setString(2, nhsNumber);
                statement.setString(3, (dateOfBirth.equals("0") ? null : dateOfBirth));
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next())
                        result = getPatientFull(resultSet);
                }
            }

        return result;
    }

    public PatientFull getPatientFull(String nhsNumber, boolean includeOnlyActivePatient) throws Exception {
        PatientFull result = null;

        String sql = "SELECT p.id,"+
                "coalesce(p.organization_id,'') as orglocation,"+
                "coalesce(o.name,'') as orgname,"+
                "coalesce(p.date_of_birth,'') as dob,"+
                "p.date_of_death as dod,"+
                "coalesce(c.name,'') as gender,"+
                "coalesce(p.nhs_number,'') as nhsNumber,"+
                "coalesce(p.last_name,'') as lastname,"+
                "coalesce(p.first_names,'') as firstname,"+
                "coalesce(p.title,'') as title,"+
                "coalesce(a.address_line_1,'') as add1,"+
                "coalesce(a.address_line_2,'') as add2,"+
                "coalesce(a.address_line_3,'') as add3,"+
                "coalesce(a.address_line_4,'') as add4,"+
                "coalesce(a.city,'') as city,"+
                "coalesce(e.usual_gp_practitioner_id,'') as practitionerId,"+
                "coalesce(e.registration_type_concept_id,'') as registrationType,"+
                "e.date_registered_end registeredEndDate,"+
                "coalesce(a.postcode,'') as postcode," +
                "coalesce(c3.description, '') as ethnicDescription," +
                "coalesce(c2.description, '') as registrationStatusValue," +
                "coalesce(e.date_registered,'') as startdate,"+
                "'HOME' as adduse,"+
                "'' as otheraddresses "+
                "FROM patient p " +
                "join organization o on o.id = p.organization_id " +
                "join patient_address a on a.id = p.current_address_id " +
                "join concept c on c.dbid = p.gender_concept_id " +
                "join episode_of_care e on e.patient_id = p.id "+
                "left join concept c3 on c3.dbid = p.ethnic_code_concept_id "+
                "join concept c2 on c2.dbid = e.registration_type_concept_id "+
                "where  "+
                " (p.nhs_number = ?)";

        if (includeOnlyActivePatient) {
            sql.concat("and c2.code = 'R' and p.date_of_death IS NULL and e.date_registered <= now() and (e.date_registered_end > now() or e.date_registered_end IS NULL)");
        }

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nhsNumber);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    result = getPatientFull(resultSet);
            }
        }

        return result;
    }

    public Map<Long, String> getPatientIds(String nhsNumber, Long id) throws Exception {
        Map<Long, String> results = new HashMap();
        String sql = "SELECT p.id, " +
                "o.ods_code as code, " +
                "o.name as display " +
                "from patient p join organization o where " +
                "(p.nhs_number = ? or p.id = ?)  and p.organization_id = o.id";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nhsNumber);
            statement.setLong(2, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    results.put(resultSet.getLong("id"), StringUtils.join(
                            resultSet.getString("code"), "#", resultSet.getString("display")));
                }
            }
        }
        return results;
    }

    public List<EpisodeOfCareFull> getEpisodeOfCareFull(List<Long> patientIds) throws Exception {
        List<EpisodeOfCareFull> episodeOfCareFullResult = new ArrayList<>();;

        String sql = "SELECT coalesce(e.date_registered, '') as dateRegistered," +
                "coalesce(e.id, '') as id, " +
                "coalesce(e.patient_id, '') as patientId, " +
                "coalesce(e.date_registered_end, '') as dateRegisteredEnd, " +
                "coalesce(e.organization_id, '') as organizationId, " +
                "coalesce(e.usual_gp_practitioner_id, '') as practitionerId, " +
                "coalesce(co.code, '') as code," +
                "coalesce(co.name, '') as name " +
                "FROM episode_of_care e join concept co on e.registration_type_concept_id = co.dbid " +
                "where e.patient_id in (" + StringUtils.join(patientIds, ',') + ") " + "order by e.organization_id";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    episodeOfCareFullResult.add(getEpisodeOfCareFull(resultSet));
            }
        }
        return episodeOfCareFullResult;
    }

    private EpisodeOfCareFull getEpisodeOfCareFull(ResultSet resultSet) throws Exception {
        EpisodeOfCareFull episodeOfCareFull = new EpisodeOfCareFull();

        episodeOfCareFull.setCode(resultSet.getString("code"))
                .setId(resultSet.getLong("id"))
                .setPatientId(resultSet.getLong("patientId"))
                .setOrganizationId(resultSet.getLong("organizationId"))
                .setPractitionerId(resultSet.getLong("practitionerId"))
                .setDateRegistered(resultSet.getString("dateRegistered"))
                .setDateRegisteredEnd(resultSet.getString("dateRegisteredEnd"))
                .setName(resultSet.getString("name"));

        return episodeOfCareFull;
    }

    public String getDescriptionFromObservation(Long id) throws Exception{
        String description = null;
        String sql = "select valueConcept.description as description from observation_additional oa " +
               " join concept propConcept on propConcept.dbid = oa.property_id " +
                "join concept valueConcept on valueConcept.dbid = oa.value_id " +
                "where propConcept.id = 'CM_ProblemSignificance' and oa.id=" + id;
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    description = String.valueOf(resultSet.getString("description"));
            }
        }
        return description;
    }


    public String getJsonValueFromObservationAdditional(Long id) throws Exception{
        String jsonString = null;
        String sql = "select oa.json_value from observation_additional oa " +
                " join concept propConcept on propConcept.dbid = oa.property_id " +
                " where propConcept.id = 'CM_ResultReferenceRange' and oa.id=" + id;

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    jsonString = String.valueOf(resultSet.getString("json_value"));
            }
        }
        return jsonString;
    }


    public List<ObservationFull> getObservationFullList(List<Long> id) throws Exception {
        List<ObservationFull> observationFullList = new ArrayList<>();

        String sql = "SELECT o.id as id," +
                "o.clinical_effective_date as date," +
                "coalesce(o.patient_id, '') as patientId, " +
                "coalesce(o.practitioner_id, '') as practitionerId, " +
                "coalesce(o.encounter_id, '') as encounterId, " +
                "coalesce(o.organization_id, '') as organizationId, " +
                "coalesce(o.result_value, '') as resultValue, " +
                "coalesce(o.result_value, '') as resultValue, " +
                "coalesce(c.code,'') as code," +
                "coalesce(c.name, '') as name, " +
                "coalesce(c.description, '') as description," +
                "coalesce(cat.description, '') as category," +
                "coalesce(o.result_value_units,'') as resultValueUnits from observation o " +
                "join concept c on o.non_core_concept_id = c.dbid " +
                "left outer join code_category_values ccv on ccv.concept_dbid = o.non_core_concept_id and ccv.code_category_id in (28,33,34,38,49) " +
                "left outer join code_category cat on cat.id = ccv.code_category_id "+
                "where o.patient_id in (" + StringUtils.join(id, ',') + ") ";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    observationFullList.add(getObservationFull(resultSet));
            }
        }

        return observationFullList;
    }


    public List<DiagnosticReportFull> getDiagnosticReportFullList(List<Long> id) throws Exception {
        List<DiagnosticReportFull> diagnosticReportFullList = new ArrayList<>();

        String sql = "SELECT o.id as id," +
                "o.clinical_effective_date as date," +
                "coalesce(o.patient_id, '') as patientId, " +
                "coalesce(o.practitioner_id, '') as practitionerId, " +
                "coalesce(o.encounter_id, '') as encounterId, " +
                "coalesce(o.organization_id, '') as organizationId, " +
                "coalesce(o.result_value, '') as resultValue, " +
                "coalesce(o.result_value, '') as resultValue, " +
                "coalesce(c.code,'') as code," +
                "coalesce(c.name, '') as name, " +
                "coalesce(c.description, '') as description," +
                "coalesce(o.result_value_units,'') as resultValueUnits, "+
                "coalesce(cat.description, '') as category from observation o " +
                "join concept c on o.non_core_concept_id = c.dbid " +
                "left outer join code_category_values obsCategory on obsCategory.concept_dbid = o.non_core_concept_id and obsCategory.code_category_id  in (28,33,38,49) " +
                "left outer join code_category cat on cat.id = obsCategory.code_category_id "+
                "where  o.patient_id in (" + StringUtils.join(id, ',') + ") " +
                "and (o.is_problem = 0  and obsCategory.code_category_id not in (34)) and o.non_core_concept_id in(13)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    diagnosticReportFullList.add(getDiagnosticReportFull(resultSet));
            }
        }

        return diagnosticReportFullList;
    }

    public PractitionerFull getPractitionerFull(long practitionerId) throws Exception {
        PractitionerFull result = null;

        String sql = "select * from practitioner pr where id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, practitionerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    result = (getPractitionerFull(resultSet));
            }
        }
        return result;
    }

    public List<ProcedureFull> getProcedureFull(List<Long> patientIds) throws Exception {
        List<ProcedureFull> procedureList = new ArrayList<>();

        String sql = "SELECT coalesce(o.clinical_effective_date, '') as date," +
                "coalesce(o.id, '') as id," +
                "coalesce(o.patient_id, '') as patientId," +
                "coalesce(o.practitioner_id, '') as practitionerId," +
                "coalesce(o.organization_id, '') as organizationId," +
                "CASE WHEN o.problem_end_date IS NULL THEN 'Active'\n" +
                "ELSE 'Past' END as status,c.name, c.code\n" +
                "FROM observation o\n" +
                "join concept c on c.dbid = o.non_core_concept_id\n" +
                "join code_category_values ccv on ccv.concept_dbid = o.non_core_concept_id " +
                "where patient_id in (" + StringUtils.join(patientIds, ',') + ") " + "and ccv.code_category_id in (37) order by o.clinical_effective_date DESC";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    procedureList.add(getProcedure(resultSet));
            }
        }
        return procedureList;
    }

    public List<TelecomFull> getTelecomFull(long patientId) throws Exception {
        List<TelecomFull> telecomFullList = new ArrayList<>();

        String sql = "select ctype.description as description1, cuse.description as description2, pc.value as value, pc.patient_id as id from patient_contact pc " +
        "join concept cuse on cuse.dbid = pc.use_concept_id " +
        "join concept ctype on ctype.dbid = pc.type_concept_id " +
                "where patient_id = " + patientId;

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    telecomFullList.add(getTelecom(resultSet));
            }
        }
        return telecomFullList;
    }

    public String[] getReligion(Integer patientId) throws Exception {
        String[] religionData = new String[2];

        String sql = "select " +
                "c.name as name, c.code as code from observation o " +
                "join concept c on o.non_core_concept_id = c.dbid " +
                "join code_category_values ccv on ccv.concept_dbid = o.non_core_concept_id " +
                "where ccv.code_category_id in (45) and " +
                "patient_id = " + patientId;

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return getDemographicsData(resultSet, religionData);
                } else {
                    return null;
                }
            }
        }
    }

    public String[] getLanguage(Integer patientId) throws Exception {
        String[] language = new String[2];

        String sql = "select " +
                "c.name as name, c.code as code from observation o " +
                "join concept c on o.non_core_concept_id = c.dbid " +
                "join code_category_values ccv on ccv.concept_dbid = o.non_core_concept_id " +
                "where ccv.code_category_id in (48) and " +
                "patient_id = " + patientId;

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return getDemographicsData(resultSet, language);
                } else {
                    return null;
                }
            }
        }
    }

    private String[] getDemographicsData(ResultSet resultSet, String[] data) throws SQLException {
        data[0] = (resultSet.getString("code"));
        data[1] =  resultSet.getString("name");
        return data;
    }

    private TelecomFull getTelecom(ResultSet resultSet) throws SQLException {
        TelecomFull telecomFull = new TelecomFull();

        telecomFull.setDescription1(resultSet.getString("description1"))
                .setDescription2(resultSet.getString("description2"))
                .setValue(resultSet.getString("value"))
                .setId(resultSet.getString("id"));
        return telecomFull;
    }


    private ProcedureFull getProcedure(ResultSet resultSet) throws SQLException {
        ProcedureFull procedureFull = new ProcedureFull();

        procedureFull.setDate(resultSet.getDate("date"))
                .setPatientId(resultSet.getLong("patientId"))
                .setStatus(resultSet.getString("status"))
                .setName(resultSet.getString("name"))
                .setId(resultSet.getString("id"))
                .setPractitionerId(resultSet.getLong("practitionerId"))
                .setOrganizationId(resultSet.getLong("organizationId"))
                .setCode(resultSet.getString("code"));
        return procedureFull;
    }

    public LocationFull getLocation(long organizationId) throws Exception {
        LocationFull locationFull = new LocationFull();

        String sql = "SELECT l.id as id, coalesce(l.name, '') as name, " +
                "coalesce(l.type_code, '') as code, " +
                "coalesce(l.type_desc,'') as description, " +
                "coalesce(l.postcode, '') as postcode " +
                "from location l " +
                "where l.managing_organization_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, organizationId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    locationFull = getLocation(resultSet);
            }
        }
        return locationFull;
    }

    private LocationFull getLocation(ResultSet resultSet) throws SQLException {
        LocationFull locationFull = new LocationFull();

        locationFull.setId(resultSet.getLong("id"))
                .setName(resultSet.getString("name"))
                .setCode(resultSet.getString("code"))
                .setDesc(resultSet.getString("description"))
                .setPostCode(resultSet.getString("postCode"));

        return locationFull;
    }

    public ObservationFull getObservationFull(ResultSet resultSet) throws SQLException {
        ObservationFull observationFull = new ObservationFull();

        observationFull.setId(resultSet.getLong("id"))
                .setPatientId(resultSet.getLong("patientId"))
                .setCode(resultSet.getString("code"))
                .setDate(resultSet.getString("date"))
                .setDescription(resultSet.getString("description"))
                .setPractitionerId(resultSet.getLong("practitionerId"))
                .setOrganizationId(resultSet.getLong("organizationId"))
                .setEncounterId(resultSet.getLong("encounterId"))
                .setName(resultSet.getString("name"))
                .setResultValue(resultSet.getDouble("resultValue"))
                .setCategory(resultSet.getString("category"))
                .setResultValueUnits(resultSet.getString("resultValueUnits"));
        return observationFull;
    }

    public DiagnosticReportFull getDiagnosticReportFull(ResultSet resultSet) throws SQLException {
        DiagnosticReportFull diagnosticReportFull = new DiagnosticReportFull();

        diagnosticReportFull.setId(resultSet.getLong("id"))
                .setPatientId(resultSet.getLong("patientId"))
                .setCode(resultSet.getString("code"))
                .setDate(resultSet.getString("date"))
                .setDescription(resultSet.getString("description"))
                .setPractitionerId(resultSet.getLong("practitionerId"))
                .setOrganizationId(resultSet.getLong("organizationId"))
                .setEncounterId(resultSet.getLong("encounterId"))
                .setName(resultSet.getString("name"))
                .setResultValue(resultSet.getDouble("resultValue"))
                .setCategory(resultSet.getString("category"))
                .setResultValueUnits(resultSet.getString("resultValueUnits"));
        return diagnosticReportFull;
    }

    public static PatientFull getPatientFull(ResultSet resultSet) throws SQLException {
        PatientFull patient = new PatientFull();

        patient
                .setId(resultSet.getString("id"))
                .setNhsNumber(resultSet.getString("nhsNumber"))
                .setGender(resultSet.getString("gender"))
                .setLastname(resultSet.getString("lastname"))
                .setTitle(resultSet.getString("title"))
                .setFirstname(resultSet.getString("firstname"))
                .setDob(resultSet.getDate("dob"))
                .setDod(resultSet.getDate("dod"))
                .setAdduse(resultSet.getString("adduse"))
                .setAdd1(resultSet.getString("add1"))
                .setAdd2(resultSet.getString("add2"))
                .setAdd3(resultSet.getString("add3"))
                .setAdd4(resultSet.getString("add4"))
                .setPostcode(resultSet.getString("postcode"))
                .setCity(resultSet.getString("city"))
                .setOtheraddresses(resultSet.getString("otheraddresses"))
                .setOrglocation(resultSet.getString("orglocation"))
                .setOrgname(resultSet.getString("orgname"))
                .setPractitionerId(resultSet.getLong("practitionerId"))
                .setRegistrationEndDate(resultSet.getDate("registeredEndDate"))
                .setRegistrationType(resultSet.getString("registrationType"))
                .setStartdate(resultSet.getDate("startdate"))
                .setRegistrationStatusValue(resultSet.getString("registrationStatusValue"))
                .setEthnicDescription(resultSet.getString("ethnicDescription"));

        return patient;
    }

    private PractitionerFull getPractitionerFull(ResultSet resultSet) throws SQLException {
        PractitionerFull practitionerFull = new PractitionerFull();

        practitionerFull.setId(resultSet.getString("id"))
                .setName(resultSet.getString("name"))
                .setRoleCode(resultSet.getString("role_code"))
                .setRoleDesc(resultSet.getString("role_Desc"));

        return practitionerFull;
    }

    public List<AllergyFull> getAllergyFullList(List<Long> patientids) throws Exception {
        ArrayList<AllergyFull> allergiesFullList =null;
        String sql = " SELECT a.id as id, a.patient_id as patientId, a.clinical_effective_date as date, c.name ,c.code,a.organization_id,a.practitioner_id " +
            " FROM allergy_intolerance a join concept c on c.dbid = a.non_core_concept_id where patient_id in (" + StringUtils.join(patientids, ',') + ")";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                allergiesFullList = getAllergyFullList(resultSet);
            }
        }

        return allergiesFullList;
    }

    /* NWL want pathology and radiology data from GP source systems removed from the extract. Once we get HL7 data in
        we need to ensure that we are sending the data from hospitals.  This will be identified by having an entry in
        observation_additional with the requesting GP organisation specified
    */
    public Set<String> getPathologyAndRadiologyList(List<Long> patientIds) throws Exception {

        Set<String> pathAndRadObservationIds = new HashSet<>();
        String sql = "SELECT " +
                "coalesce(o.id, '') as id " +
                "FROM observation o " +
                "join code_category_values ccv on ccv.concept_dbid = o.non_core_concept_id " +
                "where patient_id in (" + StringUtils.join(patientIds, ',') + ") " + "and ccv.code_category_id in (33, 38) ";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next())
                    pathAndRadObservationIds.add(resultSet.getString("id"));
            }
        }
        return pathAndRadObservationIds;
    }

    public List<AllergyFull> getAllergyFullListFromObservation(List<Long> patientids) throws Exception {
        ArrayList<AllergyFull> allergiesFullList =null;
        String sql = "select o.id as id, o.patient_id as patientId, o.clinical_effective_date as date, c.name ,c.code,o.organization_id,o.practitioner_id from observation o " +
                "join code_category_values ccv on ccv.concept_dbid = o.non_core_concept_id " +
                "join concept c on c.dbid = o.non_core_concept_id " +
                "where ccv.code_category_id in (2,3) and o.patient_id in  (" + StringUtils.join(patientids, ',') + ")";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                allergiesFullList = getAllergyFullList(resultSet);
            }
        }
        return allergiesFullList;
    }

    public OrganizationFull getOrganizationFull(long organizationId) throws Exception {

        String sql = "select id as id," +
                     "coalesce(ods_code,'') as ods_code," +
                     "coalesce(name,'') as name," +
                     "coalesce(postcode,'') as postcode  from organization where id= ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, organizationId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return getOrganizationFull(resultSet);
            }
        }

        return new OrganizationFull();
    }

    public static OrganizationFull getOrganizationFull(ResultSet resultSet) throws SQLException {
        OrganizationFull organizationFull = new OrganizationFull();
        organizationFull
                .setId(resultSet.getLong("id"))
                .setOdsCode(resultSet.getString("ods_code"))
                .setName(resultSet.getString("name"))
                .setPostCode(resultSet.getString("postcode"));

        return organizationFull;
    }

    public ArrayList<AllergyFull> getAllergyFullList(ResultSet resultSet) throws SQLException {
        ArrayList<AllergyFull> allergylist=new ArrayList<AllergyFull>();
        if(null !=resultSet) {
            while (resultSet.next()) {
                AllergyFull allergyDtls = new AllergyFull();
                allergyDtls
                        .setId(resultSet.getLong("id"))
                        .setPatientId(resultSet.getLong("patientId"))
                        .setDate(resultSet.getDate("date"))
                        .setName(resultSet.getString("name"))
                        .setCode(resultSet.getString("code"))
                        .setOrganizationId(resultSet.getLong("organization_id"))
                       .setPractitionerId(resultSet.getLong("practitioner_id"));
                allergylist.add(allergyDtls);
            }
        }
        return allergylist;
    }

    /**
     *
     * @param patientIds
     * @return
     * @throws Exception
     */
    public List<MedicationStatementFull> getMedicationStatementFullList(List<Long> patientIds) throws Exception {
        List<MedicationStatementFull> result = null;
        String sql = "select ms.id as msid, c.name, c.code, " +
                "coalesce(ms.clinical_effective_date,'') as clinicalEffDt, " +
                "coalesce(ms.patient_id,'') as patientId, " +
                "coalesce(ms.dose,'') as dose, " +
                "coalesce(ms.quantity_value,'') as qValue, " +
                "coalesce(ms.cancellation_date,'') as cancellation_date, " +
                "coalesce(ms.quantity_unit,'') as qUnit, \n" +
                "max(coalesce(mo.clinical_effective_date,'')) as valueDtTime, " +
                "ms.authorisation_type_concept_id as atCid " +
                "from medication_statement ms join medication_order mo on ms.id=mo.medication_statement_id and ms.patient_id = mo.patient_id " +
                "join concept c on c.dbid=ms.non_core_concept_id where ms.patient_id in (" + StringUtils.join(patientIds, ',') + ") " +  "group by msid";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                result = getMedicationStatementFullList(resultSet);
            }
        }
        return result;
    }

    /**
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static List<MedicationStatementFull> getMedicationStatementFullList(ResultSet resultSet) throws SQLException {
        List<MedicationStatementFull> medicationStatementList = new ArrayList<MedicationStatementFull>();
        while (resultSet.next()) {
            MedicationStatementFull medicationStatement = new MedicationStatementFull();
            medicationStatement
                    .setId(resultSet.getLong("msid"))
                    .setPatientId(resultSet.getLong("patientId"))
                    .setName(resultSet.getString("name"))
                    .setCode(resultSet.getString("code"))
                    .setDate(resultSet.getString("clinicalEffDt"))
                    .setDose(resultSet.getString("dose"))
                    .setCancellationDate(resultSet.getString("cancellation_date"))
                    .setValueDateTime(resultSet.getString("valueDtTime"));
            medicationStatementList.add(medicationStatement);
        }
        return medicationStatementList;
    }

    /**
     *
     * @param msId
     * @return
     * @throws Exception
     */
    public List<MedicationOrderFull> getMedicationOrderFullList(long msId, List<Long> patientIds) throws Exception {
        List<MedicationOrderFull> result = null;
        String sql = "SELECT mo.id, mo.practitioner_id as prid, mo.organization_id as oid, mo.medication_statement_id as msid, mo.clinical_effective_date as clinicalEffDt, mo.dose, mo.quantity_unit as qUnit, " +
                "mo.quantity_value as qValue FROM medication_order mo where mo.medication_statement_id=? " +
                " and mo.patient_id in (" + StringUtils.join(patientIds, ',') + ") " +
                "order by clinical_effective_date, msid";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, msId);
            try (ResultSet resultSet = statement.executeQuery()) {
                result = getMedicationOrderFullList(resultSet);
            }
        }
        return result;
    }

    /**
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static List<MedicationOrderFull> getMedicationOrderFullList(ResultSet resultSet) throws SQLException {
        List<MedicationOrderFull> medicationOrderList = new ArrayList<MedicationOrderFull>();
        while (resultSet.next()) {
            MedicationOrderFull medicationOrder = new MedicationOrderFull();
            medicationOrder
                    .setId(resultSet.getLong("id"))
                    .setPractitionerId(resultSet.getLong("prid"))
                    .setOrgId(resultSet.getLong("oid"))
                    .setDate(resultSet.getString("clinicalEffDt"))
                    .setDose(resultSet.getString("dose"))
                    .setQValue(resultSet.getDouble("qValue"))
                    .setQUnit(resultSet.getString("qUnit"));
            medicationOrderList.add(medicationOrder);
        }
        return medicationOrderList;
    }

    public List<ConditionFull>  getConditionFullList(List<Long> patientIds) throws Exception {
        ArrayList<ConditionFull> conditionFullList =null;
        String sql = "SELECT a.id as id, a.patient_id as patientId, a.clinical_effective_date as date,IF(ISNULL(a.problem_end_date), 'active', 'resolved') " +
                "AS ClinicalStatus, c.name ,c.code, cat2.description as category, a.is_problem as problem " +
                "FROM observation a join concept c on c.dbid = a.non_core_concept_id " +
                "join code_category_values ccv on ccv.concept_dbid = a.non_core_concept_id " +
                "join code_category cat on cat.id = ccv.code_category_id " +
                "left join code_category_values ccv2 on ccv2.concept_dbid = a.non_core_concept_id and ccv2.code_category_id in (28,33,34,38,49) " +
                "left join code_category cat2 on cat2.id = ccv2.code_category_id " +
                "where ccv.code_category_id = 8 and patient_id in (" + StringUtils.join(patientIds, ',') + ")";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                conditionFullList= getConditionFullList(resultSet);
            }
        }
        return conditionFullList;
    }

    public  ArrayList<ConditionFull> getConditionFullList(ResultSet resultSet) throws SQLException {
        ArrayList<ConditionFull> conditionlist=new ArrayList<ConditionFull>();
        if(null !=resultSet) {
            while (resultSet.next()) {
                ConditionFull conditionDtls = new ConditionFull();
                conditionDtls
                        .setId(resultSet.getLong("id"))
                        .setPatientId(resultSet.getLong("patientId"))
                        .setDate(resultSet.getDate("date"))
                        .setName(resultSet.getString("name"))
                        .setCode(resultSet.getString("code"))
                        .setCategory(resultSet.getString("category"))
                        .setProblem(resultSet.getBoolean("problem"))
                        .setClinicalStatus(resultSet.getString("ClinicalStatus"));

                conditionlist.add(conditionDtls);
            }
        }
        return conditionlist;
    }

    public List<EncounterFull> getEncounterFullList(List<Long> patientIds, Long encounterId, boolean isPatient) throws Exception {
        ArrayList<EncounterFull> encounterFullList =null;
        String sql = " SELECT  e.patient_id as patientId, e.practitioner_id as practitioner_id,e.organization_id as organization_id,e.clinical_effective_date as date, e.episode_of_care_id as episode_of_care_id, e.end_date as endDate, e.id,coalesce(c.name,'') as name ,coalesce(c.code,'') as code,  CASE WHEN e.end_date IS NULL THEN 'Active' ELSE 'Past' END as status " +
                     " FROM encounter e LEFT JOIN concept c on c.dbid = e.non_core_concept_id ";
        String where_clause="";
        if (isPatient) {
            where_clause = " where e.patient_id in (" + StringUtils.join(patientIds, ',') + ")";
        } else {
            where_clause = " where e.id = " + encounterId;
        }
        sql=sql+where_clause;

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                encounterFullList = getEncounterFullList(resultSet);
            }
        }

        return encounterFullList;
    }

    public ArrayList<EncounterFull> getEncounterFullList(ResultSet resultSet) throws SQLException {
        ArrayList<EncounterFull> encounterFullList=new ArrayList<EncounterFull>();
        if(null !=resultSet) {
            while (resultSet.next()) {
                EncounterFull encounterFull = new EncounterFull();
                encounterFull
                        .setPatientId(resultSet.getLong("patientId"))
                        .setPractitionerId(resultSet.getLong("practitioner_id"))
                        .setOrganizationId(resultSet.getLong("organization_id"))
                        .setDate(resultSet.getString("date"))
                        .setEndDate(resultSet.getString("endDate"))
                        .setName(resultSet.getString("name"))
                        .setCode(resultSet.getString("code"))
                        .setEncounterid(resultSet.getLong("id"))
                        .setStatus(resultSet.getString("status"))
                        .setEpisode_of_care_id(resultSet.getString("episode_of_care_id"));

                encounterFullList.add(encounterFull);
            }
        }
        return encounterFullList;
    }


    //immunizations

    public List<ImmunizationFull> getImmunizationsFullList(List<Long> patientIds) throws Exception {
        ArrayList<ImmunizationFull> immunizationFullList =null;
        String sql = " SELECT o.id as id, o.patient_id as patientId, o.clinical_effective_date as cfd, coalesce(o.encounter_id ,'') as encounterid ,coalesce(o.practitioner_id,'') as practitionerid, c.name ,c.code  " +
                     " FROM observation o  join concept c on c.dbid = o.non_core_concept_id " +
                    " join code_category_values ccv on ccv.concept_dbid = o.non_core_concept_id "+
                     " where patient_id in (" + StringUtils.join(patientIds, ',') + ") " +  " and ccv.code_category_id in (21) ";

        /* sql = " SELECT o.clinical_effective_date as cfd, coalesce(o.encounter_id ,'') as encounterid ,coalesce(o.practitioner_id,'') as practitionerid, c.name ,c.code  " +
                " FROM observation o  join concept c on c.dbid = o.non_core_concept_id " +
                " where patient_id = ?";
                System.out.println(sql);*/


        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                immunizationFullList = getImmunizationFullList(resultSet);
            }
        }

        return immunizationFullList;
    }

    public ArrayList<ImmunizationFull> getImmunizationFullList(ResultSet resultSet) throws SQLException {
        ArrayList<ImmunizationFull> immunizationFullList=new ArrayList<ImmunizationFull>();
        if(null !=resultSet) {
            while (resultSet.next()) {
                ImmunizationFull immunizationFull = new ImmunizationFull();
                immunizationFull
                        .setId(resultSet.getLong("id"))
                        .setPatientId(resultSet.getLong("patientId"))
                        .setName(resultSet.getString("name"))
                        .setCode(resultSet.getString("code"))
                        .setClinicalEffectiveDate(resultSet.getDate("cfd"))
                        .setEncounterID(resultSet.getString("encounterid"))
                        .setPractitionerID(resultSet.getString("practitionerid"));




                immunizationFullList.add(immunizationFull);
            }
        }
        return immunizationFullList;
    }

    /**
     *
     * @param patientIds
     * @return
     * @throws Exception
     */
    public List<AppointmentFull> getAppointmentFullList(List<Long> patientIds) throws Exception {
        List<AppointmentFull> result = null;
        String sql = "SELECT a.id, a.patient_id as patientId, a.schedule_id as sId, a.practitioner_id as prId, a.organization_id as oId, " +
                "a.actual_duration as actualDura, a.start_date as startDt, a.planned_duration as plannedDura, s.type,c.name as appointment_status " +
                " FROM appointment a join schedule s on a.schedule_id = s.id" +
                " join concept c on c.dbid = a.appointment_status_concept_id  where a.patient_id in (" + StringUtils.join(patientIds, ',') + ") ";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    result = getAppointmentFullList(resultSet);
                }
            }
        return result;
    }

    /**
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static List<AppointmentFull> getAppointmentFullList(ResultSet resultSet) throws SQLException {
        List<AppointmentFull> appointmentList = new ArrayList<AppointmentFull>();
        while (resultSet.next()) {
            AppointmentFull appointment = new AppointmentFull();
            appointment
                    .setId(resultSet.getLong("id"))
                    .setPatientId(resultSet.getLong("patientId"))
                    .setActualDuration(resultSet.getInt("actualDura"))
                    .setStartDate(resultSet.getString("startDt"))
                    .setPlannedDuration(resultSet.getInt("plannedDura"))
                    .setType(resultSet.getString("type"))
                    .setOrgId(resultSet.getLong("oId"))
                    .setPractitionerId(resultSet.getLong("prId"))
                    .setScheduleId(resultSet.getLong("sId"))
                    .setStatus(resultSet.getString("appointment_status"));

            appointmentList.add(appointment);
        }
        return appointmentList;
    }

    /**
     *
     * @param patientIds
     * @return
     * @throws Exception
     */
    public List<FamilyMemberHistoryFull> getFamilyMemberHistoryFullList(List<Long> patientIds) throws Exception {
        List<FamilyMemberHistoryFull> result = null;
        String sql = "SELECT o.id as id, o.patient_id as patientId, o.clinical_effective_date as date, " +
                "CASE WHEN o.problem_end_date IS NULL THEN 'Active' " +
                "ELSE 'Past' END as status,c.name,c.code " +
                "FROM observation o " +
                "join concept c on c.dbid = o.non_core_concept_id \n "+
                "join code_category_values ccv on ccv.concept_dbid = o.non_core_concept_id\n "+
                "where patient_id in (" + StringUtils.join(patientIds, ',') + ") " + " and ccv.code_category_id in (17) order by o.clinical_effective_date DESC";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    result = getFamilyMemberHistoryFullList(resultSet);
                }
            }

        return result;
    }

    /**
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static List<FamilyMemberHistoryFull> getFamilyMemberHistoryFullList(ResultSet resultSet) throws SQLException {
        List<FamilyMemberHistoryFull> familyMemberHistoryList = new ArrayList<FamilyMemberHistoryFull>();
        while (resultSet.next()) {
            FamilyMemberHistoryFull familyMemberHistory = new FamilyMemberHistoryFull();
            familyMemberHistory
                    .setId(resultSet.getLong("id"))
                    .setPatientId(resultSet.getLong("patientId"))
                    .setDate(resultSet.getString("date"))
                    .setStatus(resultSet.getString("status"))
                    .setName(resultSet.getString("name"))
                    .setCode(resultSet.getString("code"));
            familyMemberHistoryList.add(familyMemberHistory);
        }
        return familyMemberHistoryList;
    }


    //ReferralRequest

    public List<ReferralRequestFull> getReferralRequestFullList(List<Long> patientIds) throws Exception {
        ArrayList<ReferralRequestFull> referralRequestFullList =null;
        String sql = "SELECT rr.id as id, rr.patient_id as patientId, rr.practitioner_id ,rr.recipient_organization_id as recipent_orgid,rr.mode as intent,rr.clinical_effective_date as authored_on, "+
                " priorityConcept.name as priority, "+
                " refferalConcept.code as type_code, refferalConcept.name as type_display, "+
                " specialityConcept.name as speciality_name, specialityConcept.code as speciality_code "+
                " FROM referral_request rr "+
                " LEFT JOIN "+
                " concept priorityConcept "+
                " ON priorityConcept.dbid  = rr.referral_request_priority_concept_id "+
                " LEFT JOIN "+
                " concept refferalConcept "+
                " ON refferalConcept.dbid = rr.referral_request_type_concept_id "+
                " LEFT JOIN " +
                " concept specialityConcept" +
                " ON specialityConcept.dbid = rr.non_core_concept_id "+

                " where rr.patient_id in (" + StringUtils.join(patientIds, ',') + ")";



        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                referralRequestFullList = getReferralRequestFullList(resultSet);
            }
        }

        return referralRequestFullList;
    }

    private ArrayList<ReferralRequestFull> getReferralRequestFullList(ResultSet resultSet) throws SQLException {
        ArrayList<ReferralRequestFull> referralRequestFullList=new ArrayList<ReferralRequestFull>();
        if(null !=resultSet) {
            while (resultSet.next()) {
                ReferralRequestFull referralRequestFull = new ReferralRequestFull();
                referralRequestFull
                        .setId(resultSet.getString("id"))
                        .setPatientId(resultSet.getLong("patientId"))
                        .setPractitionerId(resultSet.getString("practitioner_id"))
                        .setRecipientOrganizationId(resultSet.getString("recipent_orgid"))
                        .setIntent(resultSet.getString("intent"))
                        .setClinicalEffectiveDate(resultSet.getDate("authored_on"))
                        .setPriority(resultSet.getString("priority"))
                        .setTypeCode(resultSet.getString("type_code"))
                        .setTypeDisplay(resultSet.getString("type_display"))
                        .setSpecialityName(resultSet.getString("speciality_name"))
                        .setSpecialityCode(resultSet.getString("speciality_code"));
                referralRequestFullList.add(referralRequestFull);
            }
        }
        return referralRequestFullList;
    }

    public Boolean applicationAccessProfile(String userId, String applicationAccessProfile) throws Exception {
        Boolean found = false;

        String sql = "select aap.name from user_manager.user_application_policy ua "+
        "join user_manager.application_policy_attribute apa on apa.application_policy_id = ua.application_policy_id "+
        "join user_manager.application_access_profile aap on aap.id = apa.application_access_profile_id "+
        "where ua.user_id = ? "+
        "and aap.name = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, userId);
            statement.setString(2, applicationAccessProfile);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    found = true;
            }
        }

        return found;
    }

    public void saveHL7Message(String wrapper, String message, String payloadId) throws Exception {
        String sql = "INSERT INTO hl7v2_inbound.imperial (date_received, message_wrapper, hl7_message, payload_id) " +
                "VALUES (?, ?, ?, ?)";

        java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, now);
            stmt.setString(2, wrapper);
            stmt.setString(3, message);
            stmt.setString(4, payloadId);
            stmt.executeUpdate();
        }
    }



}
