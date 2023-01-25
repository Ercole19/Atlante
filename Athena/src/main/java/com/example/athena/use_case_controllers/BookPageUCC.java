package com.example.athena.use_case_controllers;

import com.example.athena.dao.UserDao;
import com.example.athena.exceptions.UserInfoException;

public class BookPageUCC {
    private final UserDao dao = new UserDao();

    public  String[] getUserName(String email) throws UserInfoException{
        return dao.getName(email);
    }

    public int getReportNumber(String vendor) throws UserInfoException {
        return dao.getTotalReport(vendor);
    }
}
