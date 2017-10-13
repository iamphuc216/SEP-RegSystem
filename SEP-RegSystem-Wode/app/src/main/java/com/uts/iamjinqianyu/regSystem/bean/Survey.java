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

    public static Survey getInstance() {
        return INSTANCE;
    }

}
