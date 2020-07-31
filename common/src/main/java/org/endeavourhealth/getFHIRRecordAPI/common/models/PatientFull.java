package org.endeavourhealth.getFHIRRecordAPI.common.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientFull {
    private static final Logger LOG = LoggerFactory.getLogger(PatientFull.class);
    private String id;
    private String nhsNumber;
    private String gender;
    private String lastname;
    private String title;
    private String firstname;
    private String dob;
    private String dod;
    private String adduse;
    private String add1;
    private String add2;
    private String add3;
    private String add4;
    private String postcode;
    private String city;
    private String otheraddresses;
    private String orglocation;
    private String orgname;
    private String startdate;
    private String ethnicDescription;
    private long practitionerId;
    private String registrationType;
    private String registrationStatusValue;
    private String registrationEndDate;

    public String getId() {
        return id;
    }

    public PatientFull setId(String id) {
        this.id = id;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public PatientFull setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEthnicDescription() {
       return  ethnicDescription;
    }

    public PatientFull setEthnicDescription(String ethnicDescription) {
        this.ethnicDescription = ethnicDescription;
        return this;
    }

    public long getPractitionerId() {
        return practitionerId;
    }

    public PatientFull setPractitionerId(long practitionerId) {
        this.practitionerId = practitionerId;
        return this;
    }

    public String getRegistrationStatusValue() {
        return registrationStatusValue;
    }

    public PatientFull setRegistrationStatusValue(String registrationStatusValue) {
        this.registrationStatusValue = registrationStatusValue;
        return this;
    }


    public String getRegistrationType() {
        return registrationType;
    }

    public PatientFull setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
        return this;
    }

    public String getRegistrationEndDate() {
        return registrationEndDate;
    }

   public PatientFull setRegistrationEndDate(Date red) {
        try {
            String pattern = "dd-MMM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            this.registrationEndDate = simpleDateFormat.format(red);
        }
        catch (Exception e) {

        }
        return this;
    }


    public String getFirstname() {
        return firstname;
    }

    public PatientFull setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PatientFull setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDob() {
        return dob;
    }

    public PatientFull setDob(Date dob) {
        try {
            String pattern = "dd-MMM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            this.dob = simpleDateFormat.format(dob);
        }
        catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this;
    }

    public String getDod() {
        return dod;
    }

    public PatientFull setDod(Date dod) {
        try {
            String pattern = "dd-MMM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            this.dod = simpleDateFormat.format(dod);
        }
        catch (Exception e) {

        }
        return this;
    }

    public String getStartdate() {
        return startdate;
    }

    public PatientFull setStartdate(Date startdate) {
        try {
            String pattern = "dd-MMM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            this.startdate = simpleDateFormat.format(startdate);
        }
        catch (Exception e) {

        }
        return this;
    }

    public String getNhsNumber() {
        return nhsNumber;
    }

    public PatientFull setNhsNumber(String nhsNumber) {
        this.nhsNumber = nhsNumber;
        return this;
    }

    public String getAdduse() {
        return adduse;
    }

    public PatientFull setAdduse(String adduse) {
        this.adduse = adduse;
        return this;
    }

    public String getAdd1() {
        return add1;
    }

    public PatientFull setAdd1(String add1) {
        this.add1 = add1;
        return this;
    }

    public String getAdd2() {
        return add2;
    }

    public PatientFull setAdd2(String add2) {
        this.add2 = add2;
        return this;
    }

    public String getAdd3() {
        return add3;
    }

    public PatientFull setAdd3(String add3) {
        this.add3 = add3;
        return this;
    }

    public String getAdd4() {
        return add4;
    }

    public PatientFull setAdd4(String add4) {
        this.add4 = add4;
        return this;
    }

    public String getPostcode() {
        return postcode;
    }

    public PatientFull setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public PatientFull setCity(String city) {
        this.city = city;
        return this;
    }

    public String getOtheraddresses() {
        return otheraddresses;
    }

    public PatientFull setOtheraddresses(String otheraddresses) {
        this.otheraddresses = otheraddresses;
        return this;
    }

    public String getOrglocation() {
        return orglocation;
    }

    public PatientFull setOrglocation(String orglocation) {
        this.orglocation = orglocation;
        return this;
    }

    public String getOrgname() {
        return orgname;
    }

    public PatientFull setOrgname(String orgname) {
        this.orgname = orgname;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public PatientFull setGender(String gender) {
        this.gender = gender;
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
