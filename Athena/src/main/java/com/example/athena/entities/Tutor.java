package com.example.athena.entities;

import com.example.athena.dao.TutorDAO;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.NoCvException;
import com.example.athena.exceptions.UserInfoException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tutor {

    private String email ;
    private  String aboutMe;
    private  String sessionInfos;
    private  String contactNumbers;
    private  String name;
    private  String surname;
    private  float averageReview;

    private File cv ;

    private int reviewNumber ;
    private  List<String> courses;

    public Tutor(String email, String tutorName, String tutorSurname, float avg, int reviewNumber, TutorDetails details) {
        this.email = email ;
        this.aboutMe = details.getAboutMe();
        this.sessionInfos = details.getSessionInfos();
        this.contactNumbers = details.getContactNumbers();
        this.name = tutorName;
        this.surname = tutorSurname;
        this.averageReview = avg;
        this.reviewNumber = reviewNumber ;
        this.courses = new ArrayList<>();
    }

    public String getSessionInfos() {
        return this.sessionInfos;
    }

    public String getContactNumbers() {
        return this.contactNumbers;
    }

    public String getAboutMe() {
        return this.aboutMe;
    }

    public float getAverageReview() {return this.averageReview;}

    public String getName() {return this.name;}

    public List<String> getCourses() {return this.courses;}

    public String getSurname() {return this.surname;}

    public void setTutorInfos(String aboutMe, String sessionInfos, String contactNumbers) {
        this.aboutMe = aboutMe;
        this.sessionInfos = sessionInfos;
        this.contactNumbers = contactNumbers;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public void setSessionInfos(String sessionInfos) {
        this.sessionInfos = sessionInfos;
    }

    public void setContactNumbers(String contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getReviewNumber() {
        return reviewNumber;
    }

    public File getCV() throws UserInfoException, NoCvException {
        if(this.cv == null) {
            this.cv = new TutorDAO().getCV(this.getEmail()) ;
        }

        return this.cv ;
    }

    public void insertCV(File cv) throws UserInfoException {
        new TutorDAO().insertCv(cv) ;
        this.cv = null ;
    }

    public void addCourse(String course) throws CourseException {
        new TutorDAO().addCourse(course) ;
        this.courses.add(course) ;
    }

    public void deleteCourse(String course) throws CourseException {
        new TutorDAO().deleteCourse(course);
        this.courses.remove(course) ;
    }

    public void updateAverage(float review) {
        this.averageReview = (this.averageReview * this.reviewNumber + review) / (this.reviewNumber +1) ;
        this.reviewNumber = this.reviewNumber +1 ;
    }

    public void saveInDB() throws UserInfoException {
        new TutorDAO().saveInDB(this) ;
    }

    public static Tutor getFromDB(String email) throws UserInfoException, CourseException{
        return new TutorDAO().getTutorFromDB(email) ;
    }

}
