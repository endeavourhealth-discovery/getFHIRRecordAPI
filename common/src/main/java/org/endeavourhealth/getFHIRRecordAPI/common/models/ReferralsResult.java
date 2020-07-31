package org.endeavourhealth.getFHIRRecordAPI.common.models;

import java.util.ArrayList;
import java.util.List;

public class ReferralsResult {
    private int page = 1;
    private int length = 0;
    private List<ReferralsSummary> results = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public ReferralsResult setPage(int page) {
        this.page = page;
        return this;
    }

    public int getLength() {
        return length;
    }

    public ReferralsResult setLength(int length) {
        this.length = length;
        return this;
    }

    public List<ReferralsSummary> getResults() {
        return results;
    }

    public ReferralsResult setResults(List<ReferralsSummary> results) {
        this.results = results;
        return this;
    }
}
