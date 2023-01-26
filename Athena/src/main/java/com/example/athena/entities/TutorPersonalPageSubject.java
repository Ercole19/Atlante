package com.example.athena.entities;

import com.example.athena.beans.TutorInfosBean;
import com.example.athena.beans.UserBean;
import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.NoCvException;
import com.example.athena.exceptions.UserInfoException;

import java.io.File;


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
        this.entity = TutorInfoEntity.getFromDB(email) ;
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



    public void addCourse(String name) throws CourseException, UserInfoException {
        if (this.entity == null) {getAllInfos(LoggedTutor.getInstance().getEmail().getMail());}
        this.entity.addCourse(name);
        super.notifyObserver();

    }
    public void deleteCourse(String name) throws CourseException, UserInfoException {
        if (this.entity == null) {getAllInfos(LoggedTutor.getInstance().getEmail().getMail());}
        this.entity.deleteCourse(name);
        super.notifyObserver();
    }


    public void updateTutorInfos(String abMe, String contactNums, String sessionInfos) throws UserInfoException, CourseException {
        if (this.entity == null) {getAllInfos(LoggedTutor.getInstance().getEmail().getMail());}
        String oldAbMe = this.entity.getAboutMe() ;
        String oldSessionInfos = this.entity.getSessionInfos() ;
        String oldContactNum = this.entity.getContactNumbers() ;

        try {
            this.entity.setTutorInfos(abMe, sessionInfos, contactNums);
            this.entity.saveInDB();
        } catch (UserInfoException e) {
            this.entity.setTutorInfos(oldAbMe, oldSessionInfos, oldContactNum) ;
            throw new UserInfoException("Error in updating personal information. Details: " + e.getMessage()) ;
        }

        super.notifyObserver();
    }

    public void getCV() throws UserInfoException, NoCvException {
        try {
            if (this.entity == null) {getAllInfos(LoggedTutor.getInstance().getEmail().getMail());}
            this.entity.getCV() ;
        } catch (CourseException e) {
            throw new UserInfoException("Error in retrieving tutor data. Details : " + e.getMessage()) ;
        }
    }

    public void insertCV(File cv) throws UserInfoException {
        try {
            if (this.entity == null) {getAllInfos(LoggedTutor.getInstance().getEmail().getMail());}
            this.entity.insertCV(cv);
        } catch (CourseException e) {
            throw new UserInfoException("Error in retrieving tutor data. Details : " + e.getMessage()) ;
        }
    }

    public void resetEntity() {
        this.entity = null;
    }
}
