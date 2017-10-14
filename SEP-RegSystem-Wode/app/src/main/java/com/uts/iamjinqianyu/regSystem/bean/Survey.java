package com.uts.iamjinqianyu.regSystem.bean;

/**
 * Created by iamph on 21/09/2017.
 */

public class Survey {
    private static final Survey INSTANCE = new Survey();
    public String religion = "";
    public float knowledge = 0;
    public float interest = 0;
    public String email = "";
    public String userType = "";
    public String firstname = "";
    public String lastname = "";
    public String phone = "";
    public String nationality = "";
    private Survey() {}

    public Survey(String religion, float knowledge, float interest, String email, String userType, String firstname, String lastname, String phone, String nationality) {
        this.religion = religion;
        this.knowledge = knowledge;
        this.interest = interest;
        this.email = email;
        this.userType = userType;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.nationality = nationality;
    }

    public String getReligion() {
        return religion;
    }

    public float getKnowledge() {
        return knowledge;
    }

    public float getInterest() {
        return interest;
    }

    public String getEmail() {
        return email;
    }

    public String getUserType() {
        return userType;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public void setKnowledge(float knowledge) {
        this.knowledge = knowledge;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public static Survey getInstance() {
        return INSTANCE;
    }

}
