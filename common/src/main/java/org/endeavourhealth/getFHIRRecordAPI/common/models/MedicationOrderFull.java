package org.endeavourhealth.getFHIRRecordAPI.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicationOrderFull {
    private long id;
    private String nhsNumber;
    private String date;
    private String dose;
    private double qValue;
    private String qUnit;
    private long practitionerId;
    private long orgId;

    public long getId() { return id; }

    public MedicationOrderFull setId(long id) {
        this.id = id;
        return this;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    public MedicationOrderFull setNhsNumber(String nhsNumber) {
        this.nhsNumber = nhsNumber;
        return this;
    }

    public String getDate() {
        return date;
    }

    public MedicationOrderFull setDate(String date) {
        this.date = date;
        return this;
    }

    public String getDose() {
        return dose;
    }

    public MedicationOrderFull setDose(String dose) {
        this.dose = dose;
        return this;
    }

    public double getQValue() {
        return qValue;
    }

    public MedicationOrderFull setQValue(double qValue) {
        this.qValue = qValue;
        return this;
    }

    public String getQUnit() {
        return qUnit;
    }

    public MedicationOrderFull setQUnit(String qUnit) {
        this.qUnit = qUnit;
        return this;
    }

    public long getPractitionerId() {
        return practitionerId;
    }

    public MedicationOrderFull setPractitionerId(long practitionerId) {
        this.practitionerId = practitionerId;
        return this;
    }

    public long getOrgId() {
        return orgId;
    }

    public MedicationOrderFull setOrgId(long orgId) {
        this.orgId = orgId;
        return this;
    }
}
