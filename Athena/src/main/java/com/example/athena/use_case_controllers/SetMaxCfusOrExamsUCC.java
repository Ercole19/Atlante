package com.example.athena.use_case_controllers;

import com.example.athena.entities.ExamsOrCfusEnum;
import com.example.athena.entities.ExamsSubject;
import com.example.athena.entities.UserDao;
import com.example.athena.exceptions.CareerStatusException;
import com.example.athena.graphical_controller.SetMaxCfusOrExamsBean;

public class SetMaxCfusOrExamsUCC {


    public void setInfos(SetMaxCfusOrExamsBean bean) throws CareerStatusException {

        ExamsSubject.getInstance().setNewMax(bean.getNewMax(), bean.getType());

    }
}
