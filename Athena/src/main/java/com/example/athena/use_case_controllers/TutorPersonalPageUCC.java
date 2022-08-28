package com.example.athena.use_case_controllers;

import com.example.athena.entities.CourseDao;
import com.example.athena.entities.TutorInfoEntity;
import com.example.athena.entities.TutorPersonalPageSubject;
import com.example.athena.entities.UserDao;
import com.example.athena.exceptions.CourseException;
import com.example.athena.graphical_controller.TutorInfosBean;
import com.example.athena.graphical_controller.UserBean;

import java.io.File;
import java.util.List;

public class TutorPersonalPageUCC {

    public void setTutorInformation(TutorInfosBean bean) {
        TutorInfoEntity entity = new TutorInfoEntity(bean.getAboutMe(), bean.getContactNumbers(), bean.getSessionInfos());
        TutorPersonalPageSubject.getInstance().setTutorInfos(entity);

    }
    public void updateTutorInformation(TutorInfosBean bean)  {
        TutorInfoEntity entity = new TutorInfoEntity(bean.getAboutMe(), bean.getSessionInfos(), bean.getContactNumbers());
        TutorPersonalPageSubject.getInstance().updateTutorInfos(entity);

    }

    public void uploadCV(File cv) {
        UserDao dao = new UserDao();
        dao.insertCv(cv);
    }

}
