package org.endeavourhealth.getFHIRRecordAPI.common.models;

import java.util.Date;

public class ReferralRequestFull {

    private String id;
    private long patientId;
    private String practitionerId;
    private String recipientOrganizationId;
    private String intent;
    private Date clinicalEffectiveDate;
    private String  priority;
    private String typeCode;
    private String typeDisplay;
    private String specialityCode;
    private String specialityName;

    public String getId() {
        return id;
    }

    public ReferralRequestFull setId(String id) {
        this.id = id;
        return this;
    }

    public long getPatientId() {
        return patientId;
    }

    public ReferralRequestFull setPatientId(long patientId) {
        this.patientId = patientId;
        return this;
    }

    public String getPractitionerId() {
        return practitionerId;
    }

    public ReferralRequestFull setPractitionerId(String practitionerId) {
        this.practitionerId = practitionerId;
        return this;
    }

    public String getRecipientOrganizationId() {
        return recipientOrganizationId;
    }

    public ReferralRequestFull setRecipientOrganizationId(String recipientOrganizationId) {
        this.recipientOrganizationId = recipientOrganizationId;
        return this;
    }

    public String getIntent() {
        return intent;
    }

    public ReferralRequestFull setIntent(String intent) {
        this.intent = intent;
        return this;
    }

    public Date getClinicalEffectiveDate() {
        return clinicalEffectiveDate;
    }

    public ReferralRequestFull setClinicalEffectiveDate(Date clinicalEffectiveDate) {
        this.clinicalEffectiveDate = clinicalEffectiveDate;
        return this;
    }

    public String getPriority() {
        return priority;
    }

    public ReferralRequestFull setPriority(String priority) {
        if(priority != null)
        this.priority = priority.toLowerCase();
        return this;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public ReferralRequestFull setTypeCode(String typeCode) {
        this.typeCode = typeCode;
        return this;
    }

    public String getTypeDisplay() {
        return typeDisplay;
    }

    public ReferralRequestFull setTypeDisplay(String typeDisplay) {
        this.typeDisplay = typeDisplay;
        return this;
    }

    public String getSpecialityCode() {
        return specialityCode;
    }

    public ReferralRequestFull setSpecialityCode(String specialityCode) {
        this.specialityCode = specialityCode;
        return this;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public ReferralRequestFull setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
        return this;
    }





}
