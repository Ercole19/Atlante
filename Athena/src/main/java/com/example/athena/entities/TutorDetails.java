package com.example.athena.entities;

public class TutorDetails {

    private final String aboutMe ;
    private final String sessionInfos ;
    private final String contactNumbers ;

    public TutorDetails(String aboutMe, String sessionInfos, String contactNumbers) {
        this.aboutMe = aboutMe ;
        this.sessionInfos = sessionInfos ;
        this.contactNumbers = contactNumbers ;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public String getSessionInfos() {
        return sessionInfos;
    }

    public String getContactNumbers() {
        return contactNumbers;
    }
}
