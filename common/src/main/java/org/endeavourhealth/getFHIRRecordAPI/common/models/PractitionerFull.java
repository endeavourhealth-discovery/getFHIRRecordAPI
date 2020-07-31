package org.endeavourhealth.getFHIRRecordAPI.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PractitionerFull {

    private String id;
    private String name;
    private String roleCode;
    private String roleDesc;

    public String getId() {
        return id;
    }

    public PractitionerFull setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PractitionerFull setName(String name) {
        this.name = name;
        return this;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public PractitionerFull setRoleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public PractitionerFull setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
        return this;
    }
}
