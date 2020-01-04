package com.hive.demo.model;

public class User {
    private int userid;
    private String username;
    private String password;
    private String notes;

    public User(int userid, String username, String password, String notes) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.notes = notes;
    }

    public User() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int id) {
        this.userid = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
