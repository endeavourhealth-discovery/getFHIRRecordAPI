package org.endeavourhealth.getFHIRRecordAPI.common.models;

import java.util.Date;

public class ConditionFull {

    private long id;
    private long patientId;
    private  String clinicalStatus;
    private Date date;
    private String code;
    private String name;
    private String category;

    private boolean problem;

    public long getId() {
        return id;
    }

    public ConditionFull setId(long id) {
        this.id = id;
        return this;
    }

    public long getPatientId() {
        return patientId;
    }

    public ConditionFull setPatientId(long patientId) {
        this.patientId = patientId;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public String getClinicalStatus() {
        return clinicalStatus;
    }

    public ConditionFull setClinicalStatus(String clinicalStatus) {
        this.clinicalStatus = clinicalStatus;
        return this;
    }

    public ConditionFull setDate(Date date) {
        this.date = (date);
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getCategory() {
        return category;
    }

    public ConditionFull setCategory(String category) {
        this.category = category;
        return this;
    }

    public ConditionFull setCode(String code) {
        this.code = code;
        return this;
    }

    public boolean isProblem() {
        return problem;
    }

    public ConditionFull setProblem(boolean problem) {
        this.problem = problem;
        return this;
    }


    public String getName() {
        return name;
    }

    public ConditionFull setName(String name) {
        this.name = toTitleCase(name);
        return this;
    }
    public String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }

}
