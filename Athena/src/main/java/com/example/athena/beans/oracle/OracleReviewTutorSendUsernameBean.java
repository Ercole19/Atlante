package com.example.athena.beans.oracle;

import com.example.athena.beans.ReviewTutorSendUsernameBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class OracleReviewTutorSendUsernameBean implements ReviewTutorSendUsernameBean  {
    private String username ;
    private String subject ;
    private LocalDate day ;
    private LocalTime startTime ;
    private LocalTime endTime ;


    public OracleReviewTutorSendUsernameBean(String username, String subject, String day, String startTime, String endTime)
    {
        setUsername(username) ;
        setSubject(subject) ;
        setDay(day) ;
        setStartTime(startTime);
        setEndTime(endTime);
    }

    private void setSubject(String subject) {
        this.subject = subject;
    }

    private void setDay(String day) throws DateTimeParseException {
        this.day = LocalDate.parse(day, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setStartTime(String time) throws DateTimeParseException {
        if(!time.matches("^(2[0-3]|[01]?\\d):([0-5]?\\d)")) throw  new DateTimeParseException("invalid time format", "", 0);
        this.startTime = LocalTime.parse(time) ;
    }

    public void setEndTime(String time) {
        if(!time.matches("^(2[0-3]|[01]?\\d):([0-5]?\\d)")) throw  new DateTimeParseException("invalid time format", "", 0);
        this.endTime = LocalTime.parse(time);
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getSubject() {
        return this.subject;
    }

    @Override
    public LocalDate getDay() {
        return this.day;
    }

    @Override
    public LocalTime getStartTime() {
        return this.startTime;
    }

    @Override
    public LocalTime getEndTime() {
        return this.endTime;
    }
}
