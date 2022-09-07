package com.example.athena.use_case_controllers;

import com.example.athena.entities.UserDao;
import com.example.athena.exceptions.UserInfoException;

public class ViewTutorPageUseCaseController {

    public void getCV (String email) throws UserInfoException {

        UserDao user = new UserDao();
        user.getCV(email);

    }
}
