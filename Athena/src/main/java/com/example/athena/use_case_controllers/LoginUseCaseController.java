package com.example.athena.use_case_controllers;

import com.example.athena.entities.*;
import com.example.athena.exceptions.FindException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.exceptions.UserNotFoundException;
import com.example.athena.beans.UserBean;


public class LoginUseCaseController {

    public UserBean findUser(UserBean bean) throws UserNotFoundException, UserInfoException, FindException {

        UserDao dao = new UserDao() ;
        if (dao.findStudent(bean.getEmail(), bean.getPassword())){

            bean.setUserFound(true);
            String role =  (String) dao.getUserType(bean.getEmail()) ;
            TutorStudentLogged roleEnum = TutorStudentLogged.valueOf((String) dao.getUserType(bean.getEmail()));
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

    public void logout() {
        User.logout() ;
    }
}
