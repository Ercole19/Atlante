package com.example.athena.entities;

import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import com.example.athena.exceptions.CourseException;
import com.example.athena.beans.normal.TutorInfosBean;
import com.example.athena.beans.normal.UserBean;
import com.example.athena.exceptions.UserInfoException;

import java.util.List;


public class TutorPersonalPageSubject extends AbstractSubject {

    private static TutorPersonalPageSubject instance = null ;
    private TutorInfoEntity entity = null ;


    private TutorPersonalPageSubject()
    {

    }

    public static synchronized TutorPersonalPageSubject getInstance()
    {
        if(instance == null)
        {
            instance = new TutorPersonalPageSubject() ;
        }

        return instance ;
    }

    private void getAllInfos(String email) throws CourseException, UserInfoException {
        UserDao userDao = new UserDao() ;
        CourseDao courseDao = new CourseDao() ;

        List<String> tutorInfos = userDao.fillTutorInfosProcedure(email);
        String[] tutorName = userDao.getName(email);
        float tutorAvgReviews = userDao.getAvg(email);
        List<String> tutorCourses = courseDao.fillCourses(email);
        this.entity = new TutorInfoEntity(tutorInfos.get(0), tutorInfos.get(1), tutorInfos.get(2), tutorName[0], tutorName[1], tutorAvgReviews, tutorCourses) ;

    }

    public TutorInfosBean getTutorInfos(UserBean bean) throws CourseException, UserInfoException {
        if(this.entity == null) { getAllInfos(bean.getEmail()); }
        TutorInfosBean returnBean = new TutorInfosBean() ;
        returnBean.setName(this.entity.getName());
        returnBean.setSurname(this.entity.getSurname());
        returnBean.setAboutMe(this.entity.getAboutMe());
        returnBean.setSessionInfos(this.entity.getSessionInfos());
        returnBean.setTutorCourses(this.entity.getCourses());
        returnBean.setContactNumbers(this.entity.getContactNumbers());
        returnBean.setAvgReview(this.entity.getAvgReview());
        return returnBean ;
    }



    public void addCourse(String name) throws CourseException  {
        CourseDao dao = new CourseDao() ;
        dao.addCourse(name);
        this.entity.getCourses().add(name) ;
        super.notifyObserver();

    }
    public void deleteCourse(String name) throws CourseException  {
        CourseDao dao = new CourseDao();
        dao.deleteCourse(name);
        this.entity.getCourses().remove(name);
        super.notifyObserver();
    }


    public void updateTutorInfos(TutorInfoEntity entity) throws UserInfoException {
        UserDao dao = new UserDao();
        dao.updateTutorInfos(entity.getAboutMe(), entity.getSessionInfos(), entity.getContactNumbers());
        this.entity.setTutorInfos(entity.getAboutMe(), entity.getSessionInfos(), entity.getContactNumbers());
        super.notifyObserver();
    }

    public void resetEntity() {
        this.entity = null;
    }



}
