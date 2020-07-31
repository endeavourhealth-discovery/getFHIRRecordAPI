package org.endeavourhealth.getFHIRRecordAPI.common.models;

import java.util.ArrayList;
import java.util.List;

public class ObservationResult {
    private int page = 1;
    private int length = 0;
    private List<ObservationSummary> results = new ArrayList<>();
    private int active = 0;

    public int getPage() {
        return page;
    }

    public ObservationResult setPage(int page) {
        this.page = page;
        return this;
    }

    public int getLength() {
        return length;
    }

    public ObservationResult setLength(int length) {
        this.length = length;
        return this;
    }

    public List<ObservationSummary> getResults() {
        return results;
    }

    public ObservationResult setResults(List<ObservationSummary> results) {
        this.results = results;
        return this;
    }

}
