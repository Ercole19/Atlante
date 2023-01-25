package com.example.athena.entities;

import com.example.athena.dao.UserDao;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.exceptions.UserInfoException;

public class LoggedStudent extends LoggedUser
{
    private int repNum ;

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
    
    public int getRepNum()
    {
        return this.repNum;
    }

    @Override
    public String getEmail()
    {
        return this.email;
    }
    
    public void initStudent(String emailStudent) throws UserInfoException
    {
       this.email = emailStudent;
       UserDao userDao = new UserDao();
       this.repNum = userDao.getTotalReport(emailStudent);
    }

}
