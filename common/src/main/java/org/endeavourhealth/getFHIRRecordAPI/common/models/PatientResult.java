package org.endeavourhealth.getFHIRRecordAPI.common.models;

import java.util.ArrayList;
import java.util.List;

public class PatientResult {
    private int page = 1;
    private int length = 0;
    private List<PatientSummary> results = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public PatientResult setPage(int page) {
        this.page = page;
        return this;
    }

    public int getLength() {
        return length;
    }

    public PatientResult setLength(int length) {
        this.length = length;
        return this;
    }

    public List<PatientSummary> getResults() {
        return results;
    }

    public PatientResult setResults(List<PatientSummary> results) {
        this.results = results;
        return this;
    }
}
