package org.endeavourhealth.getFHIRRecordAPI.common.models;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticsResult {
    private int page = 1;
    private int length = 0;
    private List<DiagnosticsSummary> results = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public DiagnosticsResult setPage(int page) {
        this.page = page;
        return this;
    }

    public int getLength() {
        return length;
    }

    public DiagnosticsResult setLength(int length) {
        this.length = length;
        return this;
    }

    public List<DiagnosticsSummary> getResults() {
        return results;
    }

    public DiagnosticsResult setResults(List<DiagnosticsSummary> results) {
        this.results = results;
        return this;
    }
}
