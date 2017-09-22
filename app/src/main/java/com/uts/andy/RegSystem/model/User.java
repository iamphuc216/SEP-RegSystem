package com.uts.andy.RegSystem.model;

/**
 * Created by iamji on 2017/9/21.
 */

public class User {
    String uID;
    String email;

    public User() {
    }

    public User(String uID, String email) {
        this.uID = uID;
        this.email = email;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
