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
        String[] infos = dao.getName(bean.getEmail()) ;
        return infos;
    }

    public List<String> getTutorInfos(UserBean bean) {
        UserDao dao = new UserDao() ;
        List<String> infos = dao.filltutorinfos(bean.getEmail());
        return infos ;
    }

    public Float getTutorReviewsAvg(UserBean bean) {
        UserDao dao = new UserDao() ;
        Float avg = dao.getAvg(bean.getEmail());
        return avg;
    }

    public List<String> getTutorCourses(UserBean bean) {
        CourseDao dao = new CourseDao() ;
        List<String> courses = dao.fillCourses(bean.getEmail());
        return courses;

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
