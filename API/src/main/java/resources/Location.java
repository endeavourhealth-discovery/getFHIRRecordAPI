package resources;

import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.endeavourhealth.getFHIRRecordAPI.common.models.LocationFull;
import org.hl7.fhir.dstu3.model.Address;
import org.hl7.fhir.dstu3.model.CodeableConcept;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.Narrative;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants.LOCATION_SYSTEM;

public class Location {
    private static final Logger LOG = LoggerFactory.getLogger(Location.class);

    public static org.hl7.fhir.dstu3.model.Location getLocationResource(LocationFull locationFull) {
        org.hl7.fhir.dstu3.model.Location location = new org.hl7.fhir.dstu3.model.Location();

        UUID uuid = UUID.randomUUID();
        location.setId(uuid.toString());
        location.setStatus(org.hl7.fhir.dstu3.model.Location.LocationStatus.ACTIVE);
        location.setName(locationFull.getName());
        location.setDescription(locationFull.getDesc());
        location.setMode(org.hl7.fhir.dstu3.model.Location.LocationMode.INSTANCE);
        location.addIdentifier()
                .setValue(String.valueOf(locationFull.getId()))
                .setSystem(ResourceConstants.SYSTEM_ID);

        Narrative value = new Narrative();
        value.setStatus(Narrative.NarrativeStatus.GENERATED);
        location.setText(value);

        Address address = new Address();
        address.setUse(Address.AddressUse.WORK);
        address.setPostalCode(locationFull.getPostCode());
        location.setAddress(address);

        location.setPhysicalType(getCode(locationFull.getCode()));
        return location;
    }

    private static CodeableConcept getCode(String code) {
        CodeableConcept codeableConcept = new CodeableConcept();
        Coding coding = new Coding();
        coding.setSystem(LOCATION_SYSTEM);
        coding.setCode(code);
        return codeableConcept.addCoding(coding);
    }

}
