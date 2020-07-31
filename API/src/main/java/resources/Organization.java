package resources;

import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.endeavourhealth.getFHIRRecordAPI.common.models.OrganizationFull;

import java.util.UUID;

public class Organization {

	public static org.hl7.fhir.dstu3.model.Organization  getOrganizationResource(OrganizationFull organizationFull)
	{
		org.hl7.fhir.dstu3.model.Organization organization = null;
		organization = new org.hl7.fhir.dstu3.model.Organization();

		if(organizationFull !=null) {
			organization.addIdentifier()
					.setSystem("https://fhir.nhs.uk/Id/ods-organization-code")
					.setValue(organizationFull.getOdsCode());
			organization.getMeta().addProfile("https://fhir.hl7.org.uk/STU3/StructureDefinition/CareConnect-Organization-1");
			organization.setName(organizationFull.getName());
			//id is hardcoded now later we need to generate dynamically
            organization.setId(UUID.randomUUID().toString());

			organization.addIdentifier()
					.setValue(String.valueOf(organizationFull.getId()))
			.setSystem(ResourceConstants.SYSTEM_ID);
			organization.addAddress()
					.setPostalCode(organizationFull.getPostCode());
		}

		return organization;
	}


}
