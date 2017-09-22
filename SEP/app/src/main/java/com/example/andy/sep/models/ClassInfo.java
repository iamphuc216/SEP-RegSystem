package com.example.andy.sep.models;


public class ClassInfo {
    private String ClassName;
    private String ClassTime;
    private String ClassSize;
    private String ClassID;

    public ClassInfo(){

    }

    public ClassInfo(String classSize, String classTime, String className, String classID) {
        this.ClassSize = classSize;
        this.ClassName = className;
        this.ClassTime = classTime;
        this.ClassID = classID;
    }

    public String getClassID() {
        return ClassID;
    }

    public String getClassName() {
        return ClassName;
    }

    public String getClassSize() {
        return ClassSize;
    }

    public String getClassTime() {
        return ClassTime;
    }

    public void setClassID(String classID) {
        ClassID = classID;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public void setClassSize(String classSize) {
        ClassSize = classSize;
    }

    public void setClassTime(String classTime) {
        ClassTime = classTime;
    }
}
