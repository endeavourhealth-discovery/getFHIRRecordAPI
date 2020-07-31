package org.endeavourhealth.getFHIRRecordAPI.common.models;

import java.util.ArrayList;
import java.util.List;

public class AppointmentResult {
    private int page = 1;
    private int length = 0;
    private List<AppointmentSummary> results = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public AppointmentResult setPage(int page) {
        this.page = page;
        return this;
    }

    public int getLength() {
        return length;
    }

    public AppointmentResult setLength(int length) {
        this.length = length;
        return this;
    }

    public List<AppointmentSummary> getResults() {
        return results;
    }

    public AppointmentResult setResults(List<AppointmentSummary> results) {
        this.results = results;
        return this;
    }
}
