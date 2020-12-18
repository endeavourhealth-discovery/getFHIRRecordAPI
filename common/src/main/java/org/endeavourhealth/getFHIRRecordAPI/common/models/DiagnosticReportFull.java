package org.endeavourhealth.getFHIRRecordAPI.common.models;

public class DiagnosticReportFull {
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

    public DiagnosticReportFull setId(long id) {
        this.id = id;
        return this;
    }

    public long getPatientId() {
        return patientId;
    }

    public DiagnosticReportFull setPatientId(long patientId) {
        this.patientId = patientId;
        return this;
    }

    public long getEncounterId() {
        return encounterId;
    }

    public DiagnosticReportFull setEncounterId(long encounterId) {
        this.encounterId = encounterId;
        return this;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public DiagnosticReportFull setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public long getPractitionerId() {
        return practitionerId;
    }

    public DiagnosticReportFull setPractitionerId(long practitionerId) {
        this.practitionerId = practitionerId;
        return this;
    }

    public String getDate() {
        return date;
    }

    public DiagnosticReportFull setDate(String date) {
        this.date = date;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DiagnosticReportFull setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getResultValue() {
        return resultValue;
    }

    public DiagnosticReportFull setResultValue(double resultValue) {
        this.resultValue = resultValue;
        return this;
    }

    public String getResultValueUnits() {
        return resultValueUnits;
    }

    public DiagnosticReportFull setResultValueUnits(String resultValueUnits) {
        this.resultValueUnits = resultValueUnits;
        return this;
    }

    public String getCode() {
        return code;
    }

    public DiagnosticReportFull setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public DiagnosticReportFull setName(String name) {
        this.name = name;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public DiagnosticReportFull setCategory(String category) {
        this.category = category;
        return this;
    }
}
