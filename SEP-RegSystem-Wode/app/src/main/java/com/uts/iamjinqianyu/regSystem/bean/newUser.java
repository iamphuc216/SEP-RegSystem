package com.uts.iamjinqianyu.regSystem.bean;

/**
 * Created by iamph on 21/09/2017.
 */

public class newUser {
    private static final User INSTANCE = new User();
    public String religion = "";
    public float knowledge = 0;
    public float interest = 0;
    public String userType = "";
    public String firstname = "";
    public String lastname = "";
    public String phone = "";
    public String nationality = "";
    private newUser() {}

    public static User getInstance() {
        return INSTANCE;
    }

}
