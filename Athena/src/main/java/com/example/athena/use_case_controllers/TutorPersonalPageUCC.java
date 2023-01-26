package com.example.athena.use_case_controllers;

import com.example.athena.beans.TutorCvInfoBean;
import com.example.athena.beans.TutorInfosBean;
import com.example.athena.entities.TutorPersonalPageSubject;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.UserInfoException;

public class TutorPersonalPageUCC {
    public void updateTutorInformation(TutorInfosBean bean) throws UserInfoException, CourseException {
        TutorPersonalPageSubject.getInstance().updateTutorInfos(bean.getAboutMe(), bean.getContactNumbers(), bean.getSessionInfos());
    }

    public void uploadCV(TutorCvInfoBean bean) throws UserInfoException{
        TutorPersonalPageSubject.getInstance().insertCV(bean.getCV());
    }

}
