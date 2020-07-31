package resources;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.ListResource;

import java.text.SimpleDateFormat;

public class AllergyList {

    public static org.hl7.fhir.dstu3.model.ListResource getAllergyListResource()
    {
        ListResource allergyList = new org.hl7.fhir.dstu3.model.ListResource();
        allergyList.setStatus(ListResource.ListStatus.CURRENT);
        allergyList.setTitle("Active Allergies");
        allergyList.setDate(new java.util.Date());
        allergyList.setMode(ListResource.ListMode.SNAPSHOT);
        allergyList.getMeta().addProfile("https://fhir.nhs.uk/STU3/StructureDefinition/CareConnect-GPC-List-1");
        CodeableConcept code = new CodeableConcept();
        code.addCoding()
                .setCode("886921000000105")
                .setSystem("http://snomed.info/sct")
                .setDisplay("Allergies and adverse reaction");
        allergyList.setCode(code);
        CodeableConcept orderbycode = new CodeableConcept();
        orderbycode.addCoding()
                .setCode(" event-date")
                .setSystem("http://hl7.org/fhir/list-order");
        allergyList.setOrderedBy(orderbycode);

       return allergyList;
    }
}
