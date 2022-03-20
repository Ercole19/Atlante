package com.example.athena.entities;

public abstract class User {

    protected static User instance = null ;
    protected static TutorStudentLogged who = TutorStudentLogged.USER ;
    protected String email ;

    public static void logout()
    {
        User.instance = null ;
    }

    public abstract String getEmail() ;
}