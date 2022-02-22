package com.example.athena.use_case_controllers;

import com.example.athena.entities.UserDao;

public class SetMaxCfusOrExamsUCC {


    public void setInfos(int infosToUpdate, boolean cfuOrExams) {

        UserDao dao = new UserDao();
        dao.setCfusOrExams(infosToUpdate, cfuOrExams);

    }
}
