package com.example.athena.entities;

public class User {

    private static User instance = null;
    private String email  ;


    private User() {}

    public static User getUser() {

        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}