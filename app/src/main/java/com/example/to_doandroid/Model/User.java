package com.example.to_doandroid.Model;

public class User {
    String id;
    String Email;
    Boolean status;

    public User() {
    }

    public User(String Email) {
        this.Email = Email;
    }

    public String getUserId() {
        return id;
    }

    public void setUserId(String id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmail() {
        return Email;
    }

}
