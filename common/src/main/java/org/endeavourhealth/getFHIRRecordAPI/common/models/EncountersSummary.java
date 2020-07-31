package org.endeavourhealth.getFHIRRecordAPI.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class EncountersSummary {
    private static final Logger LOG = LoggerFactory.getLogger(EncountersSummary.class);

    private String date;
    private String type;
    private String location;
    private String practitioner;
    private String orgName;

    public String getDate() { return date; }
    public EncountersSummary setDate(Date date) {
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

    public String getType() {
        return type;
    }
    public EncountersSummary setType(String type) {
        this.type = type;
        return this;
    }

    public String getLocation() {
        return location;
    }
    public EncountersSummary setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getPractitioner() {
        return practitioner;
    }
    public EncountersSummary setPractitioner(String practitioner) {
        this.practitioner = practitioner;
        return this;
    }

    public String getOrgName() {
        return orgName;
    }

    public EncountersSummary setOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

}
