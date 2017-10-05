package com.example.andy.sep.models;



public class Announcement {
    private String Titles;
    private String Contents;
    private String Date;

    public Announcement() {
    }

    public Announcement(String content, String titles, String date) {
        this.Contents = content;
        this.Titles = titles;
        this.Date = date;
    }

    public String getDate() {
        return Date;
    }

    public String getTitles() {
        return Titles;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        this.Contents = contents;
    }

    public void setTitles(String titles) {
        this.Titles = titles;
    }

    public void setDate(String date) {
        this.Date = date;
    }
}
