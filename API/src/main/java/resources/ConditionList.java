package resources;

import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.ListResource;

public class ConditionList {

    public static ListResource getConditionListResource()
    {
        ListResource conditionList = new ListResource();
        conditionList.setStatus(ListResource.ListStatus.CURRENT);
        conditionList.setTitle("Problems");
        conditionList.setDate(new java.util.Date());
        conditionList.setMode(ListResource.ListMode.SNAPSHOT);
        conditionList.getMeta().addProfile("https://fhir.hl7.org.uk/STU3/StructureDefinition/CareConnect-List-1");
        CodeableConcept code = new CodeableConcept();
        code.addCoding()
                .setCode("717711000000103")
                .setSystem("http://snomed.info/sct")
                .setDisplay("Problems");

        CodeableConcept orderbycode = new CodeableConcept();
        code.addCoding()
                .setCode(" event-date")
                .setSystem("http://hl7.org/fhir/list-order");
        conditionList.setOrderedBy(orderbycode);

        return conditionList;
    }
}
