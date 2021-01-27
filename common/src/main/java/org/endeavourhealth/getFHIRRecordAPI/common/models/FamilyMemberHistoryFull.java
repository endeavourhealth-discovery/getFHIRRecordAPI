package org.endeavourhealth.getFHIRRecordAPI.common.models;

public class FamilyMemberHistoryFull {
    private long id;
    private long patientId;
    private String date;
    private String status;
    private String name;
    private String code;
    private long practitionerId;

    public long getId() {
        return id;
    }

    public FamilyMemberHistoryFull setId(long id) {
        this.id = id;
        return this;
    }

    public String getDate() {
        return date;
    }

    public FamilyMemberHistoryFull setDate(String date) {
        this.date = date;
        return this;
    }

    public long getPractitionerId() {
        return practitionerId;
    }

    public FamilyMemberHistoryFull setPractitionerId(long practitionerId) {
        this.practitionerId = practitionerId;
        return this;
    }

    public String getStatus() { return status; }

    public FamilyMemberHistoryFull setStatus(String status) {
        this.status = status;
        return this;
    }

    public long getPatientId() {
        return patientId;
    }

    public FamilyMemberHistoryFull setPatientId(long patientId) {
        this.patientId = patientId;
        return this;
    }

    public String getName() {
        return name;
    }

    public FamilyMemberHistoryFull setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public FamilyMemberHistoryFull setCode(String code) {
        this.code = code;
        return this;
    }

}
