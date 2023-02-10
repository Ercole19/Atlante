package com.example.athena.entities;

import com.example.athena.beans.LoggedUserMailBean;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.exceptions.StudentInfoException;

public class LoggedStudent extends LoggedUser
{
    private Student currentStudent;

    private LoggedStudent()
    {

    }

    public static LoggedStudent getInstance() throws LoggedUserException
    {
        if(LoggedUser.instance == null)
        {
            LoggedUser.instance = new LoggedStudent() ;
            LoggedUser.who = TutorStudentLogged.STUDENT ;
        }

        if(LoggedUser.who == TutorStudentLogged.TUTOR)
        {
            throw new LoggedUserException("Accessed as a Student while logged as a Tutor") ;
        }

        return (LoggedStudent) LoggedUser.instance ;
    }

    @Override
    public LoggedUserMailBean getEmail()
    {
        LoggedUserMailBean value = new LoggedUserMailBean() ;
        value.setMail(this.email);
        return value ;
    }
    
    public void initStudent(String emailStudent) throws StudentInfoException
    {
       this.email = emailStudent;
       this.currentStudent = new Student(this.email);
    }

    public Student getCurrentStudent() {
        return this.currentStudent;
    }

}
