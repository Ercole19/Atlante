package com.example.athena.use_case_controllers;

import com.example.athena.beans.UserBean;
import com.example.athena.dao.UserDao;
import com.example.athena.exceptions.NoCvException;
import com.example.athena.exceptions.UserInfoException;

public class ViewTutorPageUseCaseController {

    public void getCV (UserBean userBean) throws UserInfoException, NoCvException {

        UserDao user = new UserDao();
        user.getCV(userBean.getEmail());

    }
}
