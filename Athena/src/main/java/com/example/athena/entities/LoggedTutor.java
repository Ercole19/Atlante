package com.example.athena.entities;

import com.example.athena.exceptions.LoggedUserException;

public class LoggedTutor extends LoggedUser
{
    private LoggedTutor()
    {

    }

    public static LoggedTutor getInstance() throws LoggedUserException
    {
        if(LoggedUser.instance == null)
        {
            LoggedUser.instance = new LoggedTutor() ;
            LoggedUser.who = TutorStudentLogged.TUTOR ;
        }

        if(LoggedUser.who == TutorStudentLogged.STUDENT)
        {
            throw new LoggedUserException("Accessed as a Tutor while logged as a Student") ;
        }

        return (LoggedTutor) LoggedUser.instance ;
    }

    @Override
    public String getEmail()
    {
        return this.email;
    }

    public void initTutor(String emailTutor)
    {
        this.email = emailTutor;
    }

}
