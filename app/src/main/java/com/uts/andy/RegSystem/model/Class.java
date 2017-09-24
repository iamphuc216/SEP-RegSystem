package com.uts.andy.RegSystem.model;

/**
 * Created by iamji on 2017/9/24.
 */

public class Class {
    String className;
    int size;

    public Class() {
    }

    public Class(String _className, int _size) {
        this.className = _className;
        this.size = _size;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

