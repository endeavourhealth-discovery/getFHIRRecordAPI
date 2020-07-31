package org.endeavourhealth.getFHIRRecordAPI.common.models;

import java.util.ArrayList;
import java.util.List;

public class EncountersResult {
    private int page = 1;
    private int length = 0;
    private List<EncountersSummary> results = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public EncountersResult setPage(int page) {
        this.page = page;
        return this;
    }

    public int getLength() {
        return length;
    }

    public EncountersResult setLength(int length) {
        this.length = length;
        return this;
    }

    public List<EncountersSummary> getResults() {
        return results;
    }

    public EncountersResult setResults(List<EncountersSummary> results) {
        this.results = results;
        return this;
    }
}
