package org.endeavourhealth.getFHIRRecordAPI.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentFull {
    private long id;
    private long patientId;
    private long scheduleId;

    public String getStatus() {
        return status;
    }

    public AppointmentFull setStatus(String status) {
        this.status = status;
        return this;
    }

    private long orgId;
    private long practitionerId;
    private int actualDuration;
    private String startDate;
    private int plannedDuration;
    private String type;
    private String status;

    public long getId() { return id; }

    public AppointmentFull setId(long id) {
        this.id = id;
        return this;
    }

    public long getPatientId() {
        return patientId;
    }

    public AppointmentFull setPatientId(long patientId) {
        this.patientId = patientId;
        return this;
    }

    public long getScheduleId() {
        return scheduleId;
    }

    public AppointmentFull setScheduleId(long scheduleId) {
        this.scheduleId = scheduleId;
        return this;
    }

    public long getOrgId() {
        return orgId;
    }

    public AppointmentFull setOrgId(long orgId) {
        this.orgId = orgId;
        return this;
    }

    public long getPractitionerId() {
        return practitionerId;
    }

    public AppointmentFull setPractitionerId(long practitionerId) {
        this.practitionerId = practitionerId;
        return this;
    }

    public int getActualDuration() {
        return actualDuration;
    }

    public AppointmentFull setActualDuration(int actualDuration) {
        this.actualDuration = actualDuration;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public AppointmentFull setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public int getPlannedDuration() {
        return plannedDuration;
    }

    public AppointmentFull setPlannedDuration(int plannedDuration) {
        this.plannedDuration = plannedDuration;
        return this;
    }

    public String getType() {
        return type;
    }

    public AppointmentFull setType(String type) {
        this.type = type;
        return this;
    }

}
