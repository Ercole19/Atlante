package com.example.athena.use_case_controllers;

import com.example.athena.entities.UserDao;

public class BookPageUCC {
    public  String[] getUserName(String email) {
        UserDao dao = new UserDao();
        return dao.getName(email);
    }
}
