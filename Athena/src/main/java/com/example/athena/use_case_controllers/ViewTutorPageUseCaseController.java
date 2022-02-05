package com.example.athena.use_case_controllers;

import com.example.athena.entities.UserDao;

public class ViewTutorPageUseCaseController {

    public void getCV (String email){

        UserDao user = new UserDao();
        user.getCV(email);

    }
}
