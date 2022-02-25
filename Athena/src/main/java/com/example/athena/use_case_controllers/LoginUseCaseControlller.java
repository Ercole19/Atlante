package com.example.athena.use_case_controllers;

import com.example.athena.entities.UserDao;
import com.example.athena.graphical_controller.UserBean;

public class LoginUseCaseControlller {

    public UserBean findUser(UserBean bean) {

        UserDao dao = new UserDao() ;
        if (dao.findStudent(bean.getEmail(), bean.getPassword())){

            bean.setUserFound(true);
            String role = (String) dao.getuserType(bean.getEmail());
            bean.setRole(role);
            return bean ;
        }

        bean.setUserFound(false);
        return bean;
    }




}
