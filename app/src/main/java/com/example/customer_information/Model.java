package com.example.customer_information;

import java.util.UUID;

public class Model {

    String id, firstName, lastName, postalAddres, email, mobileOne, mobileTwo, mobileThree, dob, anniversary;

    public Model(){}

    public Model(String id, String firstName, String lastName, String postalAddres, String email, String mobileOne, String mobileTwo,
                 String mobileThree, String dob, String anniversary){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalAddres = postalAddres;
        this.email = email;
        this.mobileOne = mobileOne;
        this.mobileTwo = mobileTwo;
        this.mobileThree = mobileThree;
        this.dob = dob;
        this.anniversary = anniversary;
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

    public String getPostalAddres() {
        return postalAddres;
    }

    public void setPostalAddres(String postalAddres) {
        this.postalAddres = postalAddres;
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
}

