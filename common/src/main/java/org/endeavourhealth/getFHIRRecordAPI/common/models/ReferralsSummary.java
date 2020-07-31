package org.endeavourhealth.getFHIRRecordAPI.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReferralsSummary {
    private static final Logger LOG = LoggerFactory.getLogger(ReferralsSummary.class);

    private String date;
    private String recipient;
    private String priority;
    private String type;
    private String mode;
    private String speciality;
    private String orgName;

    public String getDate() { return date; }
    public ReferralsSummary setDate(Date date) {
        try {
            String pattern = "dd-MMM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            this.date = simpleDateFormat.format(date);
        }
        catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this;
    }

    public String getRecipient() {
        return recipient;
    }
    public ReferralsSummary setRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public String getPriority() {
        return priority;
    }
    public ReferralsSummary setPriority(String priority) {
        this.priority = priority.toLowerCase();
        return this;
    }

    public String getType() {
        return type;
    }
    public ReferralsSummary setType(String type) {
        this.type = type;
        return this;
    }

    public String getMode() {
        return mode;
    }
    public ReferralsSummary setMode(String mode) {
        this.mode = mode;
        return this;
    }

    public String getSpeciality() {
        return speciality;
    }
    public ReferralsSummary setSpeciality(String speciality) {
        this.speciality = speciality;
        return this;
    }

    public String getOrgName() {
        return orgName;
    }

    public ReferralsSummary setOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

}
