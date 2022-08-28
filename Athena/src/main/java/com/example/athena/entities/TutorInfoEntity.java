package com.example.athena.entities;

import java.util.List;

public class TutorInfoEntity {
    private  String aboutMe;
    private  String sessionInfos;
    private  String contactNumbers;
    private  String name;
    private  String surname;
    private  float avgReview;
    private  List<String> courses;

    public TutorInfoEntity(String abMe, String sesInf, String contNum, String tutorName, String tutorSurname, float avg, List<String> coursesList) {
        this.aboutMe = abMe;
        this.sessionInfos = sesInf;
        this.contactNumbers = contNum;
        this.name = tutorName;
        this.surname = tutorSurname;
        this.avgReview = avg;
        this.courses = coursesList;
    }

    public TutorInfoEntity (String aboutMe, String contactNumbers, String sessionInfos) {
        this.aboutMe = aboutMe;
        this.sessionInfos = sessionInfos;
        this.contactNumbers = contactNumbers;
        this.name = null;
        this.surname = null;
        this.avgReview = 0;
        this.courses = null;
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

    public float getAvgReview() {return this.avgReview;}

    public String getName() {return this.name;}

    public List<String> getCourses() {return this.courses;}

    public String getSurname() {return this.surname;}

    public void setTutorInfos(String aboutMe, String sessionInfos, String contactNumbers) {
        this.aboutMe = aboutMe;
        this.sessionInfos = sessionInfos;
        this.contactNumbers = contactNumbers;
    }

}
