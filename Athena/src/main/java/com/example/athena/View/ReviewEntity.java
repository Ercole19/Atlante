package com.example.athena.View;

import javafx.util.converter.LocalTimeStringConverter;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReviewEntity
{
    private String reviewCode ;
    private String studentUsername ;
    private String tutorUsername ;
    private SubjectLabels subject ;
    private LocalDate day ;
    private LocalTime startTime ;
    private LocalTime endTime ;


    public ReviewEntity(String reviewCode,String username, SubjectLabels subject, LocalDate day, int startHour, int startMinute,
                                       int endHour, int endMinute)
    {
        setReviewCode(reviewCode);
        setStudentUsername(username) ;
        setTutorUsername(user.getUser().getEmail()) ;
        setSubject(subject) ;
        setDay(day) ;
        setStartTime(startHour, startMinute) ;
        setEndTime(endHour, endMinute) ;
    }

    public void toDB()
    {
        ReviewDAO reviewDao = new ReviewDAO() ;
        reviewDao.addReview(this.reviewCode, this.tutorUsername, this.studentUsername, this.day, this.subject, this.startTime, this.endTime) ;
    }

    public void setReviewCode(String reviewCode)
    {
        this.reviewCode = reviewCode ;
    }

    public String getReviewCode()
    {
        return this.reviewCode ;
    }

    public void setStudentUsername(String username)
    {
        this.studentUsername = username ;
    }

    public String getStudentUsername()
    {
        return this.studentUsername ;
    }

    public void setTutorUsername(String username)
    {
        this.tutorUsername = username ;
    }

    public String getTutorUsername()
    {
        return this.tutorUsername ;
    }

    public void setSubject(SubjectLabels subject)
    {
        this.subject = subject ;
    }

    public SubjectLabels getSubject()
    {
        return this.subject ;
    }

    public void setDay(LocalDate day)
    {
        this.day = day ;
    }

    public LocalDate getDay()
    {
        return this.day ;
    }

    public void setStartTime(int hour, int minute)
    {
        this.startTime = LocalTime.of(hour, minute) ;
    }

    public LocalTime getStartTime()
    {
        return this.startTime ;
    }

    public void setEndTime(int hour, int minute)
    {
        this.endTime = LocalTime.of(hour, minute) ;
    }

    public LocalTime getEndTime()
    {
        return this.endTime ;
    }
}
