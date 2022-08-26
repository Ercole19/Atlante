package com.example.athena.use_case_controllers;

import com.example.athena.entities.UserDao;

public class BookPageUCC {
    private final UserDao dao = new UserDao();

    public  String[] getUserName(String email) {
        return dao.getName(email);
    }

    public int getReportNumber(String vendor) {
        return dao.getTotalReport(vendor);
    }
}
