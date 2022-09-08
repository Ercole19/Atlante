package com.example.athena.entities;

import com.example.athena.exceptions.TutorReviewException;
import com.example.athena.beans.ReviewTutorSendUsernameBean;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReviewEntity
{
    private String reviewCode ;
    private String studentUsername ;
    private String tutorUsername ;
    private String subject ;
    private LocalDate day ;
    private LocalTime startTime ;
    private LocalTime endTime ;


    public ReviewEntity(ReviewTutorSendUsernameBean usernameBean, String reviewCode)
    {
        setReviewCode(reviewCode) ;
        setStudentUsername(usernameBean.getUsername()) ;
        setTutorUsername(Tutor.getInstance().getEmail()) ;
        setSubject(usernameBean.getSubject()) ;
        setDay(usernameBean.getDay()) ;
        setStartTime(usernameBean.getStartTime()) ;
        setEndTime(usernameBean.getEndTime()) ;
    }

    public ReviewEntity(String reviewCode, String tutorUsername, String studentUsername, String subject, LocalDate day,
                        LocalTime startTime, LocalTime endTime)
    {
        setReviewCode(reviewCode);
        setStudentUsername(studentUsername) ;
        setTutorUsername(tutorUsername) ;
        setSubject(subject) ;
        setDay(day) ;
        setStartTime(startTime) ;
        setEndTime(endTime) ;
    }

    public void toDB() throws TutorReviewException
    {
        ReviewDAO reviewDao = new ReviewDAO() ;
        reviewDao.addReview(this.reviewCode, this.tutorUsername, this.studentUsername, this.day, this.subject, this.startTime, this.endTime) ;
    }

    public static ReviewEntity getFromDB(String reviewCode) throws TutorReviewException
    {
        ReviewDAO reviewDAO = new ReviewDAO() ;
        return reviewDAO.getReview(reviewCode) ;
    }

    public static void removeFromDB(String reviewCode) throws TutorReviewException
    {
        ReviewDAO reviewDAO = new ReviewDAO() ;
        reviewDAO.deleteReview(reviewCode) ;
    }

    public static void finalizeReview (int reviewStars , String reviewCode) throws TutorReviewException {
        ReviewDAO reviewDAO = new ReviewDAO() ;
        reviewDAO.finalizee(reviewCode , reviewStars) ;

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

    public void setSubject(String subject)
    {
        this.subject = subject ;
    }

    public String getSubject()
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

    public void setStartTime(LocalTime time)
    {
        this.startTime = time ;
    }

    public LocalTime getStartTime()
    {
        return this.startTime ;
    }

    public void setEndTime(int hour, int minute)
    {
        this.endTime = LocalTime.of(hour, minute) ;
    }

    public void setEndTime(LocalTime time)
    {
        this.endTime = time ;
    }

    public LocalTime getEndTime()
    {
        return this.endTime ;
    }
}
