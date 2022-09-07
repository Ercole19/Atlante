package com.example.athena.entities;

import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.exceptions.UserInfoException;

public class Student extends User
{
    private int repNum ;

    private Student()
    {

    }

    public static Student getInstance() throws LoggedUserException
    {
        if(User.instance == null)
        {
            User.instance = new Student() ;
            User.who = TutorStudentLogged.STUDENT ;
        }

        if(User.who == TutorStudentLogged.TUTOR)
        {
            throw new LoggedUserException("Accessed as a Student while logged as a Tutor") ;
        }

        return (Student)User.instance ;
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
