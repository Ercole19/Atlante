package com.example.athena.entities;

import com.example.athena.beans.LoggedUserMailBean;

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
        TutorPersonalPageSubject.getInstance().resetEntity();
    }

    public abstract LoggedUserMailBean getEmail() ;
}