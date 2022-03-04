package com.example.athena.use_case_controllers;

import com.example.athena.entities.CourseDao;
import com.example.athena.entities.UserDao;
import com.example.athena.graphical_controller.TutorInfosBean;
import com.example.athena.graphical_controller.UserBean;

import java.io.File;
import java.util.List;

public class TutorPersonalPageUCC {


    public String[] getTutorName(UserBean bean) {
        UserDao dao = new UserDao() ;
        return dao.getName(bean.getEmail());
    }

    public List<String> getTutorInfos(UserBean bean) {
        UserDao dao = new UserDao() ;
        return dao.filltutorinfos(bean.getEmail());
    }

    public Float getTutorReviewsAvg(UserBean bean) {
        UserDao dao = new UserDao() ;
        return dao.getAvg(bean.getEmail());
    }

    public List<String> getTutorCourses(UserBean bean) {
        CourseDao dao = new CourseDao() ;
        return dao.fillCourses(bean.getEmail());

    }

    public void setTutorInformation(TutorInfosBean bean) {
        UserDao dao = new UserDao();
        dao.settutorinfos(bean.getAboutMe(), bean.getSessionInfos(), bean.getContactNumbers());
    }
    public void updateTutorInformation(TutorInfosBean bean)  {
        UserDao dao = new UserDao();
        dao.updatetutorinfos(bean.getAboutMe(), bean.getSessionInfos(), bean.getContactNumbers());

    }

    public void uploadCV(File cv) {
        UserDao dao = new UserDao();
        dao.inserisciCV(cv);
    }

}
