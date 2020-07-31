package resources;

import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.endeavourhealth.getFHIRRecordAPI.common.models.AllergyFull;
import org.hl7.fhir.dstu3.model.CodeableConcept;

import java.util.UUID;

public class AllergyIntolerance {

	public static org.hl7.fhir.dstu3.model.AllergyIntolerance getAllergyIntoleranceResource(AllergyFull allergyfull)
	{
		org.hl7.fhir.dstu3.model.AllergyIntolerance allergy = new org.hl7.fhir.dstu3.model.AllergyIntolerance();

		allergy.setClinicalStatus(org.hl7.fhir.dstu3.model.AllergyIntolerance.AllergyIntoleranceClinicalStatus.ACTIVE);
		allergy.setVerificationStatus(org.hl7.fhir.dstu3.model.AllergyIntolerance.AllergyIntoleranceVerificationStatus.CONFIRMED);
		allergy.setType(org.hl7.fhir.dstu3.model.AllergyIntolerance.AllergyIntoleranceType.ALLERGY);
		allergy.getMeta().addProfile("https://fhir.hl7.org.uk/STU3/StructureDefinition/CareConnect-AllergyIntolerance-1");
		allergy.addIdentifier()
				.setValue(String.valueOf(allergyfull.getId()))
				.setSystem(ResourceConstants.SYSTEM_ID);

		// manifestation or codeable concept?
		CodeableConcept code = new CodeableConcept();
		code.addCoding()
				.setCode(allergyfull.getCode())
				.setSystem("http://snomed.info/sct")
				.setDisplay(allergyfull.getName());
		allergy.setId(UUID.randomUUID().toString());
        allergy.setCode(code);

		//allergy.setAssertedDate(allergyfull.getDate());
		allergy.setOnset(new org.hl7.fhir.dstu3.model.DateTimeType(allergyfull.getDate()));

		return allergy;
	}

}
