package com.uts.iamjinqianyu.regSystem.bean;

/**
 * Created by iamji on 2017/9/24.
 */

public class Class {
    String className;
    String size;

    public Class() {
    }

    public Class(String _className, String _size) {
        this.className = _className;
        this.size = _size;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

