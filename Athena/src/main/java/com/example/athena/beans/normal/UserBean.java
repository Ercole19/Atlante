package com.example.athena.beans.normal;

public class UserBean {

    private String email;
    private String password ;
    private String role ;
    private String name ;
    private String surname ;
    private boolean userFound;
    private String code ;



    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(String ruolo) {
        this.role = ruolo ;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUserFound(boolean userFound) {
        this.userFound = userFound;
    }

    public boolean isUserFound() {
        return userFound;
    }

    public void setCode(String code) {
        this.code = code ;
    }

    public String getCode() {
        return this.code ;
    }
}
