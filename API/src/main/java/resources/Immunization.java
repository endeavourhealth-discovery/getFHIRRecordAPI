package resources;


import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.endeavourhealth.getFHIRRecordAPI.common.models.ImmunizationFull;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.Extension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Immunization {


    public static org.hl7.fhir.dstu3.model.Immunization getImmunizationResource(ImmunizationFull immunizationFull) {

        org.hl7.fhir.dstu3.model.Immunization immunization = new org.hl7.fhir.dstu3.model.Immunization();
        immunization.setId(UUID.randomUUID().toString());
        immunization.setStatus(org.hl7.fhir.dstu3.model.Immunization.ImmunizationStatus.COMPLETED);
        immunization.setPrimarySource(true);
        immunization.setDate(immunizationFull.getClinicalEffectiveDate());
        immunization.getMeta().addProfile("https://fhir.nhs.uk/STU3/StructureDefinition/CareConnect-GPC-Immunization-1");
        immunization.addIdentifier()
                .setValue(String.valueOf(immunizationFull.getId()))
                .setSystem(ResourceConstants.SYSTEM_ID);

        Extension extension = new Extension();
        extension.setUrl("https://fhir.nhs.uk/STU3/StructureDefinition/Extension-CareConnect-GPC-DateRecorded-1");
        DateTimeType dateTimeType = (DateTimeType) extension.addChild("valueDateTime");
        dateTimeType.setValue(immunizationFull.getClinicalEffectiveDate());
        immunization.addExtension(extension);


        Extension extension1 = new Extension();
        List<Coding> coding=new ArrayList<>();
        coding.add(new Coding("http://snomed.info/sct",immunizationFull.getCode(),immunizationFull.getName()));
        CodeableConcept codeableConcept = (CodeableConcept) extension1.addChild("valueCodeableConcept");
        codeableConcept.setCoding(coding);
        immunization.addExtension(extension1);


        org.hl7.fhir.dstu3.model.Immunization.ImmunizationExplanationComponent expl=new org.hl7.fhir.dstu3.model.Immunization.ImmunizationExplanationComponent();
        List<Coding> coding1=new ArrayList<>();
        coding1.add(new Coding().setSystem("http://snomed.info/sct"));
        expl.addReason().setCoding(coding1);
        immunization.setExplanation(expl);

        return immunization;
    }


}
