package resources;

import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.endeavourhealth.getFHIRRecordAPI.common.models.FamilyMemberHistoryFull;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Narrative;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FamilyMemberHistory {

	public static org.hl7.fhir.dstu3.model.FamilyMemberHistory getFamilyMemberHistoryResource(FamilyMemberHistoryFull familyMemberHistoryResult) throws Exception {
		String date = replaceNull(familyMemberHistoryResult.getDate());
		String status = replaceNull(familyMemberHistoryResult.getStatus());
		String name = replaceNull(familyMemberHistoryResult.getName());
		String code = replaceNull(familyMemberHistoryResult.getCode());
		UUID id = UUID.randomUUID();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dt  = formatter.parse(date);

		org.hl7.fhir.dstu3.model.FamilyMemberHistory familyMemberHistory = new org.hl7.fhir.dstu3.model.FamilyMemberHistory();

		familyMemberHistory.getMeta().addProfile(ResourceConstants.FAMILY_HISTORY_PROFILE);
		familyMemberHistory.setId(String.valueOf(id));
		familyMemberHistory.addIdentifier()
				.setValue(String.valueOf(familyMemberHistoryResult.getId()))
				.setSystem(ResourceConstants.SYSTEM_ID);


		if("Active".equalsIgnoreCase(status))
			familyMemberHistory.setStatus(org.hl7.fhir.dstu3.model.FamilyMemberHistory.FamilyHistoryStatus.COMPLETED);
		else if("Past".equalsIgnoreCase(status))
			familyMemberHistory.setStatus(org.hl7.fhir.dstu3.model.FamilyMemberHistory.FamilyHistoryStatus.PARTIAL);

		familyMemberHistory.setDate(dt);
		familyMemberHistory.getText().setStatus(Narrative.NarrativeStatus.GENERATED);

		Coding coding = new Coding();
		coding.setSystem("http://snomed.info/sct");
		coding.setCode(code);
		coding.setDisplay(name);
		familyMemberHistory.addCondition().getCode().addCoding(coding);
		
		return familyMemberHistory;
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	public static String replaceNull(String input) {
		return input == null ? "" : input;
	}

}
