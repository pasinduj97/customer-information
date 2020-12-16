package com.example.customer_information;

import java.util.UUID;

public class Model {

    String id, firstName, lastName, postalAddresNo, postalAddressl1, postalAddresl2, email, mobileOne, mobileTwo, mobileThree, dob, anniversary, type, initials, country, postalC;

    public Model(){}

    public Model(String id, String firstName, String lastName, String postalAddresNo, String postalAddressl1, String postalAddresl2, String email, String mobileOne, String mobileTwo, String mobileThree, String dob, String anniversary, String type, String initials, String country, String postalC) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalAddresNo = postalAddresNo;
        this.postalAddressl1 = postalAddressl1;
        this.postalAddresl2 = postalAddresl2;
        this.email = email;
        this.mobileOne = mobileOne;
        this.mobileTwo = mobileTwo;
        this.mobileThree = mobileThree;
        this.dob = dob;
        this.anniversary = anniversary;
        this.type = type;
        this.initials = initials;
        this.country = country;
        this.postalC = postalC;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostalAddresNo() {
        return postalAddresNo;
    }

    public void setPostalAddresNo(String postalAddresNo) {
        this.postalAddresNo = postalAddresNo;
    }

    public String getPostalAddressl1() {
        return postalAddressl1;
    }

    public void setPostalAddressl1(String postalAddressl1) {
        this.postalAddressl1 = postalAddressl1;
    }

    public String getPostalAddresl2() {
        return postalAddresl2;
    }

    public void setPostalAddresl2(String postalAddresl2) {
        this.postalAddresl2 = postalAddresl2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileOne() {
        return mobileOne;
    }

    public void setMobileOne(String mobileOne) {
        this.mobileOne = mobileOne;
    }

    public String getMobileTwo() {
        return mobileTwo;
    }

    public void setMobileTwo(String mobileTwo) {
        this.mobileTwo = mobileTwo;
    }

    public String getMobileThree() {
        return mobileThree;
    }

    public void setMobileThree(String mobileThree) {
        this.mobileThree = mobileThree;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(String anniversary) {
        this.anniversary = anniversary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalC() {
        return postalC;
    }

    public void setPostalC(String postalC) {
        this.postalC = postalC;
    }
}

