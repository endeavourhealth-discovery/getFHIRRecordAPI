package org.endeavourhealth.getFHIRRecordAPI.common.models;

import java.util.Date;

public class ProcedureFull {
    private String id;
    private long patientId;
    private Date date;
    private String status;
    private String name;
    private String code;
    private long practitionerId;
    private long organizationId;

    public String getId() {
        return id;
    }

    public ProcedureFull setId(String id) {
        this.id = id;
        return this;
    }

    public long getPatientId() {
        return patientId;
    }

    public ProcedureFull setPatientId(long patientId) {
        this.patientId = patientId;
        return this;
    }

    public long getPractitionerId() {
        return practitionerId;
    }

    public ProcedureFull setPractitionerId(long practitionerId) {
        this.practitionerId = practitionerId;
        return this;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public ProcedureFull setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public ProcedureFull setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ProcedureFull setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProcedureFull setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ProcedureFull setCode(String code) {
        this.code = code;
        return this;
    }
}
