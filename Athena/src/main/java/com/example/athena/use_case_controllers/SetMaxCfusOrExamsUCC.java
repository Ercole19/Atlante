package com.example.athena.use_case_controllers;

import com.example.athena.entities.ExamsOrCfusEnum;
import com.example.athena.entities.UserDao;

public class SetMaxCfusOrExamsUCC {


    public void setInfos(int infosToUpdate, ExamsOrCfusEnum cfuOrExams) {

        UserDao dao = new UserDao();
        dao.setCfusOrExams(infosToUpdate, cfuOrExams);

    }
}
