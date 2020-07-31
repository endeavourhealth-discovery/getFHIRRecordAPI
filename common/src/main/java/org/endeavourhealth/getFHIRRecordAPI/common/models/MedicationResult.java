package org.endeavourhealth.getFHIRRecordAPI.common.models;

import java.util.ArrayList;
import java.util.List;

public class MedicationResult {
    private int page = 1;
    private int length = 0;
    private List<MedicationSummary> results = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public MedicationResult setPage(int page) {
        this.page = page;
        return this;
    }

    public int getLength() {
        return length;
    }

    public MedicationResult setLength(int length) {
        this.length = length;
        return this;
    }

    public List<MedicationSummary> getResults() {
        return results;
    }

    public MedicationResult setResults(List<MedicationSummary> results) {
        this.results = results;
        return this;
    }
}
