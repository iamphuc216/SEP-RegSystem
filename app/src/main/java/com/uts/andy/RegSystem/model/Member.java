package com.uts.andy.RegSystem.model;

/**
 * Created by iamji on 2017/9/16.
 */

public abstract class Member {
    String _username;
    String _firstName;
    String _lastName;
    int _phoneNumber;
    String _email;

    public Member() {
    }

    public Member(String _username, String _firstName, String _lastName, int _phoneNumber, String _email) {
        this._username = _username;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._phoneNumber = _phoneNumber;
        this._email = _email;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_firstName() {
        return _firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String get_lastName() {
        return _lastName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public int get_phoneNumber() {
        return _phoneNumber;
    }

    public void set_phoneNumber(int _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }
}
