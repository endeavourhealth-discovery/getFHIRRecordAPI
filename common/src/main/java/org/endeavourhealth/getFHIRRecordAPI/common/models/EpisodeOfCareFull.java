package org.endeavourhealth.getFHIRRecordAPI.common.models;

public class EpisodeOfCareFull {
    private long id;
    private long patientId;
    private String code;
    private String name;
    private String dateRegistered;
    private String dateRegisteredEnd;
    private long organizationId;
    private long practitionerId;

    public long getId() {
        return id;
    }

    public EpisodeOfCareFull setId(long id) {
        this.id = id;
        return this;
    }

    public long getPatientId() {
        return patientId;
    }

    public EpisodeOfCareFull setPatientId(long patientId) {
        this.patientId = patientId;
        return this;
    }

    public String getCode() {
        return code;
    }

    public EpisodeOfCareFull setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public EpisodeOfCareFull setName(String name) {
        this.name = name;
        return this;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public EpisodeOfCareFull setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
        return this;
    }

    public String getDateRegisteredEnd() {
        return dateRegisteredEnd;
    }

    public EpisodeOfCareFull setDateRegisteredEnd(String dateRegisteredEnd) {
        this.dateRegisteredEnd = dateRegisteredEnd;
        return this;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public EpisodeOfCareFull setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public long getPractitionerId() {
        return practitionerId;
    }

    public EpisodeOfCareFull setPractitionerId(long practitionerId) {
        this.practitionerId = practitionerId;
        return this;
    }


}
