package resources;

import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.endeavourhealth.getFHIRRecordAPI.common.dal.JDBCDAL;
import org.endeavourhealth.getFHIRRecordAPI.common.models.ObservationFull;
import org.hl7.fhir.dstu3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.UUID;

import static org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants.*;

public class Observation {
    private static final Logger LOG = LoggerFactory.getLogger(Observation.class);
    private ObservationFull observationFull;
    private JDBCDAL jdbcdal;

    public Observation(ObservationFull observationFull, JDBCDAL jdbcdal) {
        this.observationFull = observationFull;
        this.jdbcdal = jdbcdal;
    }

    public org.hl7.fhir.dstu3.model.Observation getObservationResource() throws Exception{
        org.hl7.fhir.dstu3.model.Observation observation = new org.hl7.fhir.dstu3.model.Observation();

        observation.setStatus(org.hl7.fhir.dstu3.model.Observation.ObservationStatus.FINAL);
        observation.setEffective(getEffectiveDateTime(observationFull.getDate())); //observation.clinical_effective_date
        observation.getMeta().addProfile(ResourceConstants.OBSERVATION_PROFILE);
        observation.addIdentifier()
                .setValue(String.valueOf(observationFull.getId()))
                .setSystem(ResourceConstants.SYSTEM_ID);
        String json = jdbcdal.getJsonValueFromObservationAdditional(observationFull.getPatientId());
        if(json != null) {
            org.hl7.fhir.dstu3.model.Observation.ObservationReferenceRangeComponent observationReferenceRangeComponent = new org.hl7.fhir.dstu3.model.Observation.ObservationReferenceRangeComponent();
            observationReferenceRangeComponent.addChild(json);
            observation.setReferenceRange(Arrays.asList(observationReferenceRangeComponent));
        }

        UUID uuid = UUID.randomUUID();
        observation.setId(String.valueOf(uuid));
       // List<Extension> extensionList = new ArrayList<>();
        Extension extension = new Extension();
        extension.setUrl(OBSERVATION_DESCRIPTION);
        StringType stringType = (StringType) extension.addChild(VALUE_STRING);
        stringType.setValue(observationFull.getName());

        if(observationFull.getResultValue() > 0.0) {
            Quantity typeQuantity = (Quantity) observation.addChild(VALUE_QUANTITY);
            typeQuantity.setValue(observationFull.getResultValue());
            typeQuantity.setSystem(OBSERVATION_QUANTITY_VALUE);
            typeQuantity.setUnit(observationFull.getResultValueUnits());
        }

        CodeableConcept codeConcept = addCodeableConcept(observationFull.getCode(), observationFull.getDescription(), ResourceConstants.OBSERVATION_SYSTEM,
                "",extension);

        observation.setCode(codeConcept);

        return observation;
    }


    private Period getEffectiveDateTime(String date) {
        Period period = new Period();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            period.setStart(format.parse(date));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return period;
    }


    private CodeableConcept addCodeableConcept(String codeValue, String displayValue, String systemValue, String idValue, Extension extension) {
        CodeableConcept code = new CodeableConcept();
        code.addCoding()
                .setCode(codeValue)
                .setDisplay(displayValue)
                .setSystem(systemValue)
                .setId(idValue).addExtension(extension);

        return code;
    }
}
