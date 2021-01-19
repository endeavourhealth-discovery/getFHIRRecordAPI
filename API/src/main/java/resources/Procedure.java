package resources;

import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.endeavourhealth.getFHIRRecordAPI.common.models.ProcedureFull;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.Narrative;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants.PROCEDURE_SYSTEM;

public class Procedure {
    private static final Logger LOG = LoggerFactory.getLogger(Procedure.class);

    public static org.hl7.fhir.dstu3.model.Procedure getProcedureResource(ProcedureFull procedureFull) {
        org.hl7.fhir.dstu3.model.Procedure procedure = new org.hl7.fhir.dstu3.model.Procedure();

        UUID uuid = UUID.randomUUID();
        procedure.setId(uuid.toString());
        procedure.setStatus(org.hl7.fhir.dstu3.model.Procedure.ProcedureStatus.COMPLETED);
        DateTimeType dateTimeType = new DateTimeType();
        dateTimeType.setValue(procedureFull.getDate());
        procedure.setPerformed(dateTimeType);

        procedure.addIdentifier()
                .setValue(String.valueOf(procedureFull.getId()))
                .setSystem(ResourceConstants.SYSTEM_ID);

        Narrative narrative = new Narrative();
        narrative.setStatus(Narrative.NarrativeStatus.GENERATED);
        procedure.setText(narrative);

        procedure.setCode(getCode(procedureFull));
        return procedure;
    }

    private static CodeableConcept getCode(ProcedureFull procedureFull) {
        CodeableConcept codeableConcept = new CodeableConcept();
        Coding coding = new Coding();
        coding.setSystem(PROCEDURE_SYSTEM);
        coding.setCode(procedureFull.getCode());
        coding.setDisplay(procedureFull.getName());
        return codeableConcept.addCoding(coding);
    }
}

