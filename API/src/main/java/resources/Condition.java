package resources;

import ca.uhn.fhir.model.api.TemporalPrecisionEnum;
import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.endeavourhealth.getFHIRRecordAPI.common.dal.JDBCDAL;
import org.endeavourhealth.getFHIRRecordAPI.common.models.ConditionFull;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;

import java.util.Arrays;
import java.util.TimeZone;
import java.util.UUID;

public class Condition {

    public static org.hl7.fhir.dstu3.model.Condition getConditionResource(ConditionFull conditionfull, JDBCDAL jdbcdal) throws Exception {
        org.hl7.fhir.dstu3.model.Condition condition = new org.hl7.fhir.dstu3.model.Condition();
        if(conditionfull.getClinicalStatus().equalsIgnoreCase("active"))
        { condition.setClinicalStatus(org.hl7.fhir.dstu3.model.Condition.ConditionClinicalStatus.ACTIVE); }
        else {
            condition.setClinicalStatus(org.hl7.fhir.dstu3.model.Condition.ConditionClinicalStatus.RESOLVED);
                 }
        condition.setOnset(new org.hl7.fhir.dstu3.model.DateTimeType(conditionfull.getDate(), TemporalPrecisionEnum.SECOND, TimeZone.getDefault()));
        condition.getMeta().addProfile("https://fhir.hl7.org.uk/STU3/StructureDefinition/CareConnect-ProblemHeader-Condition-1");

        condition.addIdentifier()
                .setValue(String.valueOf(conditionfull.getId()))
                .setSystem(ResourceConstants.SYSTEM_ID);

        String description = jdbcdal.getDescriptionFromObservation(conditionfull.getId());
        if(description != null) {
            CodeableConcept problemSignificanceCode = new CodeableConcept();
            problemSignificanceCode.addCoding()
                    .setDisplay(description)
                    .setSystem("http://endeavourhealth.org/fhir/ValueSet/primarycare-problem-significance");

            condition.addExtension()
                    .setUrl("http://endeavourhealth.org/fhir/StructureDefinition/primarycare-problem-significance-extension")
                    .setValue(problemSignificanceCode);

        }
        // manifestation or codeable concept?
       /* CodeableConcept code = new CodeableConcept();
        code.setText(conditionfull.getName());
        code.addCoding()
                .setCode(conditionfull.getCode())
                .setSystem("http://snomed.info/sct")
                .setDisplay(conditionfull.getName());
        condition.setId(UUID.randomUUID().toString());
        condition.setCode(code);*/

        CodeableConcept category = new CodeableConcept();
        Coding coding = new Coding();
        coding.setSystem("http://terminology.hl7.org/CodeSystem/condition-category");
        if(conditionfull.isProblem()){
            coding.setDisplay("Problems").setCode("problem-list-item");
        } else {
            coding.setDisplay("Encounter Diagnosis").setCode("encounter-diagnosis");
        }
        category.addCoding(coding);
        condition.setCategory(Arrays.asList(category));

        return condition;
    }
}
