package com.example.athena.use_case_controllers;

import com.example.athena.entities.TutorInfoEntity;
import com.example.athena.entities.TutorPersonalPageSubject;
import com.example.athena.entities.UserDao;
import com.example.athena.beans.normal.TutorInfosBean;
import com.example.athena.exceptions.UserInfoException;

import java.io.File;

public class TutorPersonalPageUCC {
    public void updateTutorInformation(TutorInfosBean bean) throws UserInfoException {
        TutorInfoEntity entity = new TutorInfoEntity(bean.getAboutMe(), bean.getContactNumbers(), bean.getSessionInfos());
        TutorPersonalPageSubject.getInstance().updateTutorInfos(entity);

    }

    public void uploadCV(File cv) throws UserInfoException{
        UserDao dao = new UserDao();
        dao.insertCv(cv);
    }

}
