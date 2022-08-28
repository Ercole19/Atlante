package com.example.athena.graphical_controller;

import java.util.List;

public class TutorInfosBean {
    private String aboutMe;
    private String contactNumbers;
    private String sessionInfos;
    private String name;
    private String surname;
    private float avgReview;
    private List<String> tutorCourses;

    public String getAboutMe() {
        return aboutMe;
    }

    public String getContactNumbers() {
        return contactNumbers;
    }

    public String getSessionInfos() {
        return sessionInfos;
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

    public List<String> getTutorCourses() {return tutorCourses;}

    public void setName(String name) {this.name = name;}

    public void setSurname(String surname) {this.surname = surname;}

    public String getSurname() {return surname;}

    public String getName() {return name;}

    public float getAvgReview() {return avgReview;}

    public void setAvgReview(float avgReview) {this.avgReview = avgReview;}

    public void setTutorCourses(List<String> tutorCourses) {this.tutorCourses = tutorCourses;}
}
