package resources;

import org.endeavourhealth.getFHIRRecordAPI.common.models.DiagnosticReportFull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.endeavourhealth.getFHIRRecordAPI.common.dal.JDBCDAL;
import org.endeavourhealth.getFHIRRecordAPI.common.models.ObservationFull;
import org.hl7.fhir.dstu3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import static org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants.*;
public class DiagnosticReport {

    private static final Logger LOG = LoggerFactory.getLogger(Observation.class);
    private DiagnosticReportFull diagnosticReportFull;
    private JDBCDAL jdbcdal;

    public DiagnosticReport(DiagnosticReportFull observationFull, JDBCDAL jdbcdal) {
        this.diagnosticReportFull = observationFull;
        this.jdbcdal = jdbcdal;
    }

    public org.hl7.fhir.dstu3.model.DiagnosticReport getDiagnosticReport() throws Exception {
        org.hl7.fhir.dstu3.model.DiagnosticReport diagnosticReport = new org.hl7.fhir.dstu3.model.DiagnosticReport();

        //diagnosticReport.setStatus(org.hl7.fhir.dstu3.model.Observation.ObservationStatus.FINAL);
        diagnosticReport.setEffective(getEffectiveDateTime(diagnosticReportFull.getDate())); //observation.clinical_effective_date
        diagnosticReport.getMeta().addProfile(ResourceConstants.OBSERVATION_PROFILE);
        diagnosticReport.addIdentifier()
                .setValue(String.valueOf(diagnosticReportFull.getId()))
                .setSystem(ResourceConstants.SYSTEM_ID);

        String description = jdbcdal.getDescriptionFromObservation(diagnosticReportFull.getId());
        if (description != null) {
            CodeableConcept problemSignificanceCode = new CodeableConcept();
            problemSignificanceCode.addCoding()
                    .setDisplay(description)
                    .setSystem("http://endeavourhealth.org/fhir/ValueSet/primarycare-problem-significance");

            diagnosticReport.addExtension()
                    .setUrl("http://endeavourhealth.org/fhir/StructureDefinition/primarycare-problem-significance-extension")
                    .setValue(problemSignificanceCode);

        }
        String json = jdbcdal.getJsonValueFromObservationAdditional(diagnosticReportFull.getId());
        if (json != null) {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(json);
            Iterator<JSONObject> iterator = jsonArray.iterator();
            org.hl7.fhir.dstu3.model.Observation.ObservationReferenceRangeComponent observationReferenceRangeComponent = new org.hl7.fhir.dstu3.model.Observation.ObservationReferenceRangeComponent();

            while (iterator.hasNext()) {
                JSONObject json1 = (JSONObject) iterator.next();
                JSONObject rangeJson = (JSONObject) json1.get("low");
                if (null != rangeJson) {
                    SimpleQuantity simpleQuantity = (SimpleQuantity) observationReferenceRangeComponent.addChild("low");

                    simpleQuantity.setValue(new java.math.BigDecimal(rangeJson.get("value").toString()));
                    simpleQuantity.setComparator(Quantity.QuantityComparator.fromCode(rangeJson.get("comparator").toString()));
                    simpleQuantity.setUnit(rangeJson.get("unit").toString());
                }
                rangeJson = (JSONObject) json1.get("high");
                if (null != rangeJson) {
                    SimpleQuantity simpleQuantity = (SimpleQuantity) observationReferenceRangeComponent.addChild("high");

                    simpleQuantity.setValue(new java.math.BigDecimal(rangeJson.get("value").toString()));
                    simpleQuantity.setComparator(Quantity.QuantityComparator.fromCode(rangeJson.get("comparator").toString()));
                    simpleQuantity.setUnit(rangeJson.get("unit").toString());
                }


            }

         //   List<org.hl7.fhir.dstu3.model.DiagnosticReport.DiagnosticReportReferenceRangeComponent> alist = diagnosticReport.getReferenceRange();
         //   alist.add(observationReferenceRangeComponent);
          //  observation.setReferenceRange(alist);
        }

        UUID uuid = UUID.randomUUID();
        diagnosticReport.setId(String.valueOf(uuid));
        // List<Extension> extensionList = new ArrayList<>();
        Extension extension = new Extension();
        extension.setUrl(OBSERVATION_DESCRIPTION);
        StringType stringType = (StringType) extension.addChild(VALUE_STRING);
        stringType.setValue(diagnosticReportFull.getName());

        if (diagnosticReportFull.getResultValue() > 0.0) {
            Quantity typeQuantity = (Quantity) diagnosticReport.addChild(VALUE_QUANTITY);
            typeQuantity.setValue(diagnosticReportFull.getResultValue());
            typeQuantity.setSystem(OBSERVATION_QUANTITY_VALUE);
            typeQuantity.setUnit(diagnosticReportFull.getResultValueUnits());
        }

        CodeableConcept codeConcept = addCodeableConcept(diagnosticReportFull.getCode(), diagnosticReportFull.getDescription(), ResourceConstants.OBSERVATION_SYSTEM,
                "", extension);

        diagnosticReport.setCode(codeConcept);
        CodeableConcept category = new CodeableConcept();
        category.setText(diagnosticReportFull.getCategory());
        diagnosticReport.setCategory(category);
        return diagnosticReport;
    }


    private Period getEffectiveDateTime(String date) {
        Period period = new Period();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            period.setStart(format.parse(date));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return period;
    }


    private CodeableConcept addCodeableConcept(String codeValue, String displayValue, String systemValue, String idValue, Extension extension) {
        CodeableConcept code = new CodeableConcept();
        code.addCoding()
                .setCode(codeValue)
                .setDisplay(displayValue)
                .setSystem(systemValue)
                .setId(idValue).addExtension(extension);

        return code;
    }
}
