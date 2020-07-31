package org.endeavourhealth.getFHIRRecordAPI.common.models;

import java.util.ArrayList;
import java.util.List;

public class AllergyResult {
    private int page = 1;
    private int length = 0;
    private List<AllergySummary> results = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public AllergyResult setPage(int page) {
        this.page = page;
        return this;
    }

    public int getLength() {
        return length;
    }

    public AllergyResult setLength(int length) {
        this.length = length;
        return this;
    }

    public List<AllergySummary> getResults() {
        return results;
    }

    public AllergyResult setResults(List<AllergySummary> results) {
        this.results = results;
        return this;
    }
}
