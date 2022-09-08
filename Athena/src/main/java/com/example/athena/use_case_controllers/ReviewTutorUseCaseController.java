package com.example.athena.use_case_controllers;

import com.example.athena.boundaries.SendCodeMailBean;
import com.example.athena.boundaries.SendReviewCodeEmailBoundary;
import com.example.athena.entities.ReviewEntity;
import com.example.athena.entities.TutorReviewCodesGenerator;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.TutorReviewException;
import com.example.athena.beans.normal.ReviewCodeBean;
import com.example.athena.beans.ReviewTutorSendUsernameBean;
import com.example.athena.beans.normal.SendReviewBean;
import com.example.athena.beans.normal.TutoringInformationBean;

import java.security.NoSuchAlgorithmException;

public class ReviewTutorUseCaseController
{
    public String generateReview(ReviewTutorSendUsernameBean usernameBean) throws TutorReviewException, SendEmailException
    {

        String reviewCode = null;
        try {
            reviewCode = TutorReviewCodesGenerator.generateReviewCode(5);
        } catch (NoSuchAlgorithmException e) {
            throw new TutorReviewException("Unable to generate review code");
        }

        ReviewEntity review = new ReviewEntity(usernameBean, reviewCode) ;
        review.toDB() ;

        SendCodeMailBean mailInformation = new SendCodeMailBean(usernameBean.getUsername(), reviewCode) ;
        SendReviewCodeEmailBoundary.getInstance().sendCode(mailInformation) ;

        return reviewCode ;
    }

    public TutoringInformationBean reviewTutor(ReviewCodeBean reviewCode) throws TutorReviewException
    {
        String code = reviewCode.getReviewCode() ;
        ReviewEntity review = ReviewEntity.getFromDB(code) ;

        TutoringInformationBean tutoringInformationBean =  new TutoringInformationBean() ;

        tutoringInformationBean.setTutorsName(review.getTutorUsername());
        tutoringInformationBean.setTutoringSubject(review.getSubject().toString());
        tutoringInformationBean.setTutoringDaysHour(review.getDay(), review.getStartTime(), review.getEndTime());
        return tutoringInformationBean;
    }

    public void sendReview(SendReviewBean review) throws TutorReviewException
    {

        ReviewEntity.finalizeReview(review.getReviewStars() , review.getCode());
    }

}
