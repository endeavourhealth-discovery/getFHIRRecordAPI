package org.endeavourhealth.getFHIRRecordAPI.common.models;

public class OrganizationFull {
    private long id;
    private String odsCode;
    private String name;
    private String postCode;

    public long getId() {
        return id;
    }

    public OrganizationFull setId(long id) {
        this.id = id;
        return this;
    }

    public String getOdsCode() {
        return odsCode;
    }

    public OrganizationFull setOdsCode(String odsCode) {
        this.odsCode = odsCode;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrganizationFull setName(String name) {
        this.name = name;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public OrganizationFull setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }



}
