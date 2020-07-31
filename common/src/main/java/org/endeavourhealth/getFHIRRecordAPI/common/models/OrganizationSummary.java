package org.endeavourhealth.getFHIRRecordAPI.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationSummary {

    private String odsCode;
    private String name;
    private String postCode;

    public String getOdsCode() {
        return odsCode;
    }

    public OrganizationSummary setOdsCode(String odsCode) {
        this.odsCode = odsCode;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrganizationSummary setName(String name) {
        this.name = name;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public OrganizationSummary setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }



}
