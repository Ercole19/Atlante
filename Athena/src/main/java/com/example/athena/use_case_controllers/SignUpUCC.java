package com.example.athena.use_case_controllers;

import com.example.athena.entities.UserDao;
import com.example.athena.graphical_controller.UserBean;

public class SignUpUCC {

    public boolean register(UserBean bean) {

        UserDao dao = new UserDao();
        if ( dao.registerUser(bean.getEmail(), bean.getPassword(), bean.getRole(), bean.getName(), bean.getSurname()) ) {
            return true ;
        }
        return false ;


    }

}
