package resources;

import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.endeavourhealth.getFHIRRecordAPI.common.models.ReferralRequestFull;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.UUID;

public class ReferralRequest {
    private static final Logger LOG = LoggerFactory.getLogger(Observation.class);
    public static org.hl7.fhir.dstu3.model.ReferralRequest getReferralRequestResource(ReferralRequestFull referralRequestFull) {

        String refId = referralRequestFull.getId();
        org.hl7.fhir.dstu3.model.ReferralRequest referralRequest = new org.hl7.fhir.dstu3.model.ReferralRequest();
        referralRequest.setId(UUID.randomUUID().toString());
        referralRequest.setStatus(org.hl7.fhir.dstu3.model.ReferralRequest.ReferralRequestStatus.ACTIVE);

        referralRequest.getMeta().addProfile(ResourceConstants.REFERREL_REQUEST_PROFILE);

        if(null!=referralRequestFull.getPriority()) {

            try {
                referralRequest.setPriority(org.hl7.fhir.dstu3.model.ReferralRequest.ReferralPriority.fromCode(referralRequestFull.getPriority()));
            } catch (Exception e) {
                referralRequest.setPriority(org.hl7.fhir.dstu3.model.ReferralRequest.ReferralPriority.ROUTINE);
            }
        }

        if(null!=referralRequestFull.getIntent()) {

            try {
                referralRequest.setIntent(org.hl7.fhir.dstu3.model.ReferralRequest.ReferralCategory.fromCode(referralRequestFull.getIntent()));
            } catch (Exception e) {
                referralRequest.setIntent(org.hl7.fhir.dstu3.model.ReferralRequest.ReferralCategory.ORDER);

            }
        }

        if(null!=referralRequestFull.getClinicalEffectiveDate())
        {
            referralRequest.setAuthoredOn(referralRequestFull.getClinicalEffectiveDate());
        }

        if(null!=referralRequestFull.getTypeCode())
        {

            CodeableConcept codeableConcept = new CodeableConcept();
            Coding coding = new Coding();
            coding.setSystem("http://snomed.info/sct");
            coding.setCode(referralRequestFull.getTypeCode());
            coding.setDisplay(referralRequestFull.getTypeDisplay());
            referralRequest.setReasonCode(Arrays.asList(codeableConcept.addCoding(coding)));
        }

        if(null!=referralRequestFull.getSpecialityCode())
        {

            CodeableConcept codeableConcept = new CodeableConcept();
            Coding coding = new Coding();
            coding.setSystem("http://orionhealth.com/fhir/apps/specialties");
            coding.setCode(referralRequestFull.getSpecialityCode());
            coding.setDisplay(referralRequestFull.getSpecialityName());
            referralRequest.setSpecialty( codeableConcept.addCoding(coding));
        }
       return referralRequest;
    }



}
