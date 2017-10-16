package com.uts.iamjinqianyu.regSystem.bean;

/**
 * Created by iamji on 2017/9/30.
 */

public class Homework {
    private String classID;
    private String content;

    public Homework() {
    }

    public Homework(String classID, String content) {
        this.classID = classID;
        this.content = content;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
