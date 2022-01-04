package com.example.athena.View;

public class user {

    private static user instance = null;
    private String email  ;


    private user() {}

    public static user getUser() {

        if (instance == null) {
            instance = new user();
        }
        return instance;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getEmail() {
        return this.email;
    }
}