package org.endeavourhealth.getFHIRRecordAPI.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicationStatementFull {
    private long id;
    private long patientId;
    private String nhsNumber;
    private String name;
    private String code;
    private String valueDateTime;
    private int status;
    private String date;
    private String dose;
    private long practitionerId;
    private long organizationId;

    private String cancellationDate;

    public long getId() {
        return id;
    }

    public MedicationStatementFull setId(long id) {
        this.id = id;
        return this;
    }

    public long getPatientId() {
        return patientId;
    }

    public MedicationStatementFull setPatientId(long patientId) {
        this.patientId = patientId;
        return this;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    public MedicationStatementFull setNhsNumber(String nhsNumber) {
        this.nhsNumber = nhsNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public MedicationStatementFull setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public MedicationStatementFull setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCancellationDate() {
        return cancellationDate;
    }

    public MedicationStatementFull setCancellationDate(String cancellationDate) {
        this.cancellationDate = cancellationDate;
        return this;
    }

    public String getValueDateTime() {
        return valueDateTime;
    }

    public MedicationStatementFull setValueDateTime(String valueDateTime) {
        this.valueDateTime = valueDateTime;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public MedicationStatementFull setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getDate() {
        return date;
    }

    public MedicationStatementFull setDate(String date) {
        this.date = date;
        return this;
    }

    public String getDose() {
        return dose;
    }

    public MedicationStatementFull setDose(String dose) {
        this.dose = dose;
        return this;
    }

    public long getPractitionerId() {
        return practitionerId;
    }

    public void setPractitionerId(long practitionerId) {
        this.practitionerId = practitionerId;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }
}
