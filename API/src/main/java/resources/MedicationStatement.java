package resources;

import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.endeavourhealth.getFHIRRecordAPI.common.models.MedicationOrderFull;
import org.endeavourhealth.getFHIRRecordAPI.common.models.MedicationStatementFull;
import org.hl7.fhir.dstu3.model.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants.VALUE_STRING;

public class MedicationStatement {

	public static org.hl7.fhir.dstu3.model.MedicationStatement getMedicationStatementResource(MedicationStatementFull medicationStatementResult) throws Exception {
		String clinicalEffDate = replaceNull(medicationStatementResult.getDate());
		String lastIssueDate = replaceNull(medicationStatementResult.getLastIssueDate());
		String dose = replaceNull(medicationStatementResult.getDose());
		String valueDateTime = replaceNull(medicationStatementResult.getValueDateTime());
		UUID id = UUID.randomUUID();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date clinicalEffDt = formatter.parse(clinicalEffDate);

		Date lastIssueDt = formatter.parse(lastIssueDate);

		org.hl7.fhir.dstu3.model.MedicationStatement medicationStatement = new org.hl7.fhir.dstu3.model.MedicationStatement();

		medicationStatement.addIdentifier()
				.setValue(String.valueOf(medicationStatementResult.getId()))
				.setSystem(ResourceConstants.SYSTEM_ID);

		medicationStatement.setStatus(medicationStatementResult.getCancellationDate().equals("") ? org.hl7.fhir.dstu3.model.MedicationStatement.MedicationStatementStatus.ACTIVE :
				org.hl7.fhir.dstu3.model.MedicationStatement.MedicationStatementStatus.COMPLETED);
		medicationStatement.setId(String.valueOf(id));
		medicationStatement.getMeta().addProfile("https://fhir.hl7.org.uk/STU3/StructureDefinition/CareConnect-MedicationStatement-1");
		medicationStatement.setTaken(org.hl7.fhir.dstu3.model.MedicationStatement.MedicationStatementTaken.UNK);

		medicationStatement.setDateAsserted(clinicalEffDt);
		//medicationStatement.getEffectivePeriod().setStart(clinicalEffDt);

		Extension extension1 = new Extension();
		extension1.setUrl("https://fhir.hl7.org.uk/STU3/StructureDefinition/Extension-CareConnect-MedicationStatementLastIssueDate-1");
		DateTimeType dateTimeType = (DateTimeType) extension1.addChild("valueDateTime");
		dateTimeType.setValue(lastIssueDt);

		Extension extension2 = new Extension();
		extension2.setUrl("https://fhir.hl7.org.uk/STU3/StructureDefinition/Extension-CareConnect-PrescribingAgency-1");

		Coding coding = new Coding();
		coding.setCode("prescribed-at-gp-practice");
		coding.setSystem("https://fhir.hl7.org.uk/STU3/CodeSystem/CareConnect-PrescribingAgency-1");
		coding.setDisplay("Prescribed at GP practice");

		List<Extension> extensionList = new ArrayList<Extension>();
		extensionList.add(extension1);
		extensionList.add(extension2);
		medicationStatement.setExtension(extensionList);

		List<Dosage> dosageList = new ArrayList<Dosage>();
		Dosage dosage = new Dosage();
		dosage.setText(dose);
        dosageList.add(dosage);
		medicationStatement.setDosage(dosageList);

		return medicationStatement;
	}

	public static org.hl7.fhir.dstu3.model.MedicationRequest getMedicationRequestResource(MedicationOrderFull medicationOrderResult) throws Exception {
		String clinicalEffDate = replaceNull(medicationOrderResult.getDate());
		String dose = replaceNull(medicationOrderResult.getDose());
		double qValue = medicationOrderResult.getQValue();
		String qUnit = replaceNull(medicationOrderResult.getQUnit());
		UUID id = UUID.randomUUID();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date clinicalEffDt = formatter.parse(clinicalEffDate);

		org.hl7.fhir.dstu3.model.MedicationRequest medicationRequest = new org.hl7.fhir.dstu3.model.MedicationRequest();

		medicationRequest.setId(String.valueOf(id));
		medicationRequest.getMeta().addProfile("https://fhir.hl7.org.uk/STU3/StructureDefinition/CareConnect-MedicationRequest-1");
		medicationRequest.setStatus(org.hl7.fhir.dstu3.model.MedicationRequest.MedicationRequestStatus.COMPLETED);
		medicationRequest.setIntent(org.hl7.fhir.dstu3.model.MedicationRequest.MedicationRequestIntent.PLAN);

		medicationRequest.setAuthoredOn(clinicalEffDt);

		List<Dosage> dosageList = new ArrayList<Dosage>();
		Dosage dosage = new Dosage();
		dosage.setText(dose);
		dosageList.add(dosage);
		medicationRequest.setDosageInstruction(dosageList);

		Extension extension = new Extension();
		extension.setUrl("https://fhir.hl7.org.uk/STU3/StructureDefinition/Extension-CareConnect-PrescriptionType-1");
		medicationRequest.addExtension(extension);

		Extension extension1 = new Extension();
		extension1.setUrl("https://fhir.hl7.org.uk/STU3/StructureDefinition/Extension-CareConnect-MedicationQuantityText-1");
		StringType stringType = (StringType) extension1.addChild(VALUE_STRING);
		stringType.setValue(qUnit);

		org.hl7.fhir.dstu3.model.MedicationRequest.MedicationRequestDispenseRequestComponent medicationRequestDispenseRequestComponent = new
						MedicationRequest.MedicationRequestDispenseRequestComponent();
		medicationRequestDispenseRequestComponent.getQuantity().setValue(qValue);
		medicationRequestDispenseRequestComponent.getQuantity().addExtension(extension1);
		medicationRequest.setDispenseRequest(medicationRequestDispenseRequestComponent);

		return medicationRequest;
	}

	public static org.hl7.fhir.dstu3.model.Medication getMedicationResource(MedicationStatementFull medicationStatementResult) throws Exception {
		String name = replaceNull(medicationStatementResult.getName());
		String code = replaceNull(medicationStatementResult.getCode());
		UUID id = UUID.randomUUID();

		org.hl7.fhir.dstu3.model.Medication medication = new org.hl7.fhir.dstu3.model.Medication();

		medication.setId(String.valueOf(id));
		medication.getMeta().addProfile("https://fhir.hl7.org.uk/STU3/StructureDefinition/CareConnect-Medication-1");

		Coding coding = new Coding();
		coding.setDisplay(name);
		coding.setCode(code);
		coding.setSystem("http://snomed.info/sct");
		CodeableConcept codeableConcept = new CodeableConcept();
		codeableConcept.addCoding(coding);
		medication.setCode(codeableConcept);

		return medication;
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
