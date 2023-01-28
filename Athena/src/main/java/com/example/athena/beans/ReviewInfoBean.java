package com.example.athena.beans;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class ReviewInfoBean {
    private String studentMail;
    private String tutoringSubject;
    private LocalDate tutoringDay;
    private LocalTime tutoringStartTime;
    private LocalTime tutoringEndTime;

    public void setTutoringSubject(String tutoringSubject) {
        this.tutoringSubject = tutoringSubject;
    }

    public void setTutoringDay(LocalDate tutoringDay) throws DateTimeParseException {
        this.tutoringDay = tutoringDay;
    }

    public void setStudentMail(String studentMail) {
        this.studentMail = studentMail;
    }

    public void setTutoringStartTime(LocalTime time) {
        this.tutoringStartTime = time ;
    }

    public void setTutoringEndTime(LocalTime time) {
        this.tutoringEndTime = time ;
    }

    public String getStudentMail() {
        return this.studentMail;
    }

    public String getTutoringSubject() {
        return this.tutoringSubject;
    }

    public LocalDate getTutoringDay() {
        return this.tutoringDay;
    }

    public LocalTime getTutoringStartTime() {
        return this.tutoringStartTime;
    }

    public LocalTime getTutoringEndTime() {
        return this.tutoringEndTime;
    }
}
