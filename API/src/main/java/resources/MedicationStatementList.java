package resources;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.ListResource;

public class MedicationStatementList {

    public static ListResource getMedicationStatementListResource()
    {
        ListResource medicationStatementList = new ListResource();
        medicationStatementList.setStatus(ListResource.ListStatus.CURRENT);
        medicationStatementList.setTitle("Medication List");
        medicationStatementList.setDate(new java.util.Date());
        medicationStatementList.setMode(ListResource.ListMode.SNAPSHOT);
        medicationStatementList.getMeta().addProfile("https://fhir.hl7.org.uk/STU3/StructureDefinition/CareConnect-List-1");
        CodeableConcept code = new CodeableConcept();
        code.addCoding()
                .setCode("933361000000108")
                .setSystem("http://snomed.info/sct")
                .setDisplay("Medications and medical devices");

        CodeableConcept orderbycode = new CodeableConcept();
        code.addCoding()
                .setCode("event-date")
                .setSystem("http://hl7.org/fhir/list-order");
        medicationStatementList.setOrderedBy(orderbycode);
        return medicationStatementList;
    }
}
