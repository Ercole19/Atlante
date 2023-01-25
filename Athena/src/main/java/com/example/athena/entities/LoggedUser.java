package com.example.athena.entities;

public abstract class LoggedUser {

    protected static LoggedUser instance = null ;
    protected static TutorStudentLogged who = TutorStudentLogged.USER ;
    protected String email ;

    public static void logout()
    {
        LoggedUser.instance = null ;
        CalendarSubject.getInstance().refreshOnLogOut() ;
        ExamsSubject.getInstance().logOut();
        BooksSubject.getInstance().logOut();
    }

    public abstract String getEmail() ;
}