package org.endeavourhealth.getFHIRRecordAPI.common.models;

public class ObservationFull {
    private long id;
    private long patientId;
    private String date;
    private String description;
    private double resultValue;
    private String resultValueUnits;
    private String code;
    private String name;
    private long organizationId;
    private long practitionerId;
    private long encounterId;

    private String category;

    public long getId() {
        return id;
    }

    public ObservationFull setId(long id) {
        this.id = id;
        return this;
    }

    public long getPatientId() {
        return patientId;
    }

    public ObservationFull setPatientId(long patientId) {
        this.patientId = patientId;
        return this;
    }

    public long getEncounterId() {
        return encounterId;
    }

    public ObservationFull setEncounterId(long encounterId) {
        this.encounterId = encounterId;
        return this;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public ObservationFull setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public long getPractitionerId() {
        return practitionerId;
    }

    public ObservationFull setPractitionerId(long practitionerId) {
        this.practitionerId = practitionerId;
        return this;
    }

    public String getDate() {
        return date;
    }

    public ObservationFull setDate(String date) {
        this.date = date;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ObservationFull setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getResultValue() {
        return resultValue;
    }

    public ObservationFull setResultValue(double resultValue) {
        this.resultValue = resultValue;
        return this;
    }

    public String getResultValueUnits() {
        return resultValueUnits;
    }

    public ObservationFull setResultValueUnits(String resultValueUnits) {
        this.resultValueUnits = resultValueUnits;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ObservationFull setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public ObservationFull setName(String name) {
        this.name = name;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ObservationFull setCategory(String category) {
        this.category = category;
        return this;
    }
}
