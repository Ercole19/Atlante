package com.example.athena.use_case_controllers;

import com.example.athena.entities.Student;
import com.example.athena.entities.Tutor;
import com.example.athena.entities.TutorStudentLogged;
import com.example.athena.entities.UserDao;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.exceptions.UserNotFoundException;
import com.example.athena.graphical_controller.UserBean;


public class LoginUseCaseController {

    public UserBean findUser(UserBean bean) throws UserNotFoundException {

        UserDao dao = new UserDao() ;
        if (dao.findStudent(bean.getEmail(), bean.getPassword())){

            bean.setUserFound(true);
            String role =  (String) dao.getuserType(bean.getEmail()) ;
            TutorStudentLogged roleEnum = TutorStudentLogged.valueOf((String) dao.getuserType(bean.getEmail()));
            if(roleEnum == TutorStudentLogged.STUDENT)
            {
                Student.getInstance().initStudent(bean.getEmail());
            }
            else
            {
                Tutor.getInstance().initTutor(bean.getEmail());
            }
            bean.setRole(role);
            return bean ;
        }

        bean.setUserFound(false);
        throw new UserNotFoundException("User not found");
    }
}
