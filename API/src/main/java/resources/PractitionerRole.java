package resources;

import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.endeavourhealth.getFHIRRecordAPI.common.models.PractitionerFull;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants.PRACTITIONER_ROLE_SYSTEM;
import static org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants.PRACTITIONER_ROLE_URL;

public class PractitionerRole {
    private static final Logger LOG = LoggerFactory.getLogger(PractitionerRole.class);

    public static org.hl7.fhir.dstu3.model.PractitionerRole getPractitionerRoleResource(PractitionerFull practitionerResult) {
        org.hl7.fhir.dstu3.model.PractitionerRole practitionerRole = new org.hl7.fhir.dstu3.model.PractitionerRole();

        UUID uuid = UUID.randomUUID();
        practitionerRole.setId(uuid.toString());

        practitionerRole.getMeta().addProfile(PRACTITIONER_ROLE_URL);
        practitionerRole.addIdentifier()
                .setValue(String.valueOf(practitionerResult.getId()))
                .setSystem(ResourceConstants.SYSTEM_ID);

        LOG.info(String.valueOf(practitionerResult.getId()) + " : setting code");
        CodeableConcept code = new CodeableConcept();
        code.addCoding()
                .setCode(practitionerResult.getRoleCode())
                .setDisplay(practitionerResult.getRoleDesc())
                .setSystem(PRACTITIONER_ROLE_SYSTEM);
        practitionerRole.addCode(code);

        LOG.info(String.valueOf(practitionerResult.getId()) + " : code set");
        return practitionerRole;
    }
}
