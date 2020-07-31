package resources;

import org.apache.commons.lang3.StringUtils;
import org.endeavourhealth.getFHIRRecordAPI.common.models.EpisodeOfCareFull;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants.EPISODE_OF_CARE_TYPE_CODING;

public class EpisodeOfCare {
    private static final Logger LOG = LoggerFactory.getLogger(EpisodeOfCare.class);

    public static org.hl7.fhir.dstu3.model.EpisodeOfCare getEpisodeOfCareResource(List<EpisodeOfCareFull> episodeOfCareFullList) {
        LOG.info("Entering getEpisodeOfCareResource() method");
        org.hl7.fhir.dstu3.model.EpisodeOfCare episodeOfCare = new org.hl7.fhir.dstu3.model.EpisodeOfCare();

        UUID uuid = UUID.randomUUID();
        episodeOfCare.setId(uuid.toString());
        episodeOfCare.setStatus(org.hl7.fhir.dstu3.model.EpisodeOfCare.EpisodeOfCareStatus.ACTIVE);

        List<org.hl7.fhir.dstu3.model.EpisodeOfCare.EpisodeOfCareStatusHistoryComponent> episodeOfCareStatusHistoryComponentList = new ArrayList<>();
        List<CodeableConcept> CodeableConceptList = new ArrayList<>();
        episodeOfCareFullList.forEach(episodeOfCareFull -> {
            org.hl7.fhir.dstu3.model.EpisodeOfCare.EpisodeOfCareStatusHistoryComponent episodeOfCareStatusHistoryComponent =
                    new org.hl7.fhir.dstu3.model.EpisodeOfCare.EpisodeOfCareStatusHistoryComponent();
            episodeOfCareStatusHistoryComponent.setStatus(org.hl7.fhir.dstu3.model.EpisodeOfCare.EpisodeOfCareStatus.PLANNED);
            episodeOfCareStatusHistoryComponent.setPeriod(getStartEndPeriod(episodeOfCareFull));
            episodeOfCareStatusHistoryComponentList.add(episodeOfCareStatusHistoryComponent);
            CodeableConceptList.add(getCode(episodeOfCareFull));
        });

        episodeOfCare.setStatusHistory(episodeOfCareStatusHistoryComponentList);

        episodeOfCare.setType(CodeableConceptList);

        Date periodDate = getDate(episodeOfCareFullList.get(0).getDateRegistered());
        if(periodDate != null) {
            episodeOfCare.setPeriod(new Period().setStart(periodDate));
        }

        return episodeOfCare;
    }

    private static Period getStartEndPeriod(EpisodeOfCareFull episodeOfCareFull) {
        Period period = new Period();
         period.setStart(getDate(episodeOfCareFull.getDateRegistered()));
         period.setEnd(getDate(episodeOfCareFull.getDateRegisteredEnd()));
        return period;
    }

    private static Date getDate(String dateString) {
        if(StringUtils.isNotEmpty(dateString)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                return format.parse(dateString);
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }
        return null;
    }

    private static CodeableConcept getCode(EpisodeOfCareFull episodeOfCareFull) {
        CodeableConcept codeableConcept = new CodeableConcept();
        Coding coding = new Coding();
        coding.setSystem(EPISODE_OF_CARE_TYPE_CODING);
        coding.setCode(episodeOfCareFull.getCode());
        coding.setDisplay(episodeOfCareFull.getName());
        return codeableConcept.addCoding(coding);
    }


}
