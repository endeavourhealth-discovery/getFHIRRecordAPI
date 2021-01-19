package resources;

import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.ListResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObservationList {
    private static final Logger LOG = LoggerFactory.getLogger(ObservationList.class);

    public static org.hl7.fhir.dstu3.model.ListResource getObservationResource() {
        ListResource observationList = new org.hl7.fhir.dstu3.model.ListResource();
        observationList.setStatus(ListResource.ListStatus.CURRENT);
        observationList.setTitle(ResourceConstants.TITLE);
        observationList.setDate(new java.util.Date());
        observationList.setMode(ListResource.ListMode.SNAPSHOT);

        observationList.getMeta().addProfile(ResourceConstants.OBSERVATION_LIST_PROFILE);
        CodeableConcept orderByCodeConcept = addCodeableConcept(ResourceConstants.OBSERVATION_LIST_CODE, "", ResourceConstants.OBSERVATION_LIST_SYSTEM,
                "");
        observationList.setOrderedBy(orderByCodeConcept);
        return observationList;
    }

    private static CodeableConcept addCodeableConcept(String codeValue, String displayValue, String systemValue, String idValue) {
        CodeableConcept code = new CodeableConcept();
        code.addCoding()
                .setCode(codeValue)
                .setDisplay(displayValue)
                .setSystem(systemValue)
                .setId(idValue);

        return code;
    }
}
