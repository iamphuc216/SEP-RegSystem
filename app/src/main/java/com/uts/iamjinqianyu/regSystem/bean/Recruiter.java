package com.uts.iamjinqianyu.regSystem.bean;

/**
 * Created by iamji on 2017/9/24.
 */

public class Recruiter {
    private String name;
    private String email;

    public Recruiter() {
    }

    public Recruiter(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
