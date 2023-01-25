package com.example.athena.beans;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class ReviewInfoBean {
    private String username ;
    private String subject ;
    private LocalDate day ;
    private LocalTime startTime ;
    private LocalTime endTime ;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDay(LocalDate day) throws DateTimeParseException {
        this.day = day ;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStartTime(LocalTime time) {
        this.startTime = time ;
    }

    public void setEndTime(LocalTime time) {
        this.endTime = time ;
    }

    public String getUsername() {
        return this.username;
    }

    public String getSubject() {
        return this.subject;
    }

    public LocalDate getDay() {
        return this.day;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }
}
