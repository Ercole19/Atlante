package com.example.athena.entities;

import com.example.athena.exceptions.LoggedUserException;

public class Tutor extends User
{
    private float starValutation ;

    private Tutor()
    {

    }

    public static Tutor getInstance() throws LoggedUserException
    {
        if(User.instance == null)
        {
            User.instance = new Tutor() ;
            User.who = TutorStudentLogged.TUTOR ;
        }

        if(User.who == TutorStudentLogged.STUDENT)
        {
            throw new LoggedUserException("Accessed as a Tutor while logged as a Student") ;
        }

        return (Tutor)User.instance ;
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
