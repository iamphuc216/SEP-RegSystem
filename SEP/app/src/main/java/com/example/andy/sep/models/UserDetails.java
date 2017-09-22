package com.example.andy.sep.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gintama on 11/09/2017.
 */

public class UserDetails {

    public String userId;
    public String first_name;
    public String last_name;
    public String religion;
    public String userType;

    public UserDetails(){

    }

    public UserDetails(String userId, String first_name, String last_name, String religion, String userType){
        this.userId = userId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.religion = religion;
        this.userType = userType;
    }

    public String getUserId() { return userId; }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getReligion() {
        return religion;
    }

    public String getUserType() {
        return userType;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("first_name", first_name);
        result.put("last_name", last_name);
        result.put("religion", religion);
        result.put("userType", userType);
        result.put("userId", userId);
        return result;
    }
    // [END post_to_map]

}
