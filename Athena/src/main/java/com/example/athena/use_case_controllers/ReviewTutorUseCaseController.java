package com.example.athena.use_case_controllers;

import com.example.athena.beans.*;
import com.example.athena.boundaries.SendCodeMailBean;
import com.example.athena.boundaries.SendReviewCodeEmailBoundary;
import com.example.athena.entities.ReviewEntity;
import com.example.athena.entities.RandomCodesGenerator;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.TutorReviewException;

import java.security.NoSuchAlgorithmException;

public class ReviewTutorUseCaseController
{
    public String insertReview(ReviewInfoBean reviewBean) throws TutorReviewException, SendEmailException
    {

        String reviewCode;
        try {
            reviewCode = RandomCodesGenerator.generateReviewCode(5);
        } catch (NoSuchAlgorithmException e) {
            throw new TutorReviewException("Unable to generate review code");
        }

        ReviewEntity review = new ReviewEntity(reviewBean, reviewCode) ;
        review.toDB() ;

        SendCodeMailBean mailInformation = new SendCodeMailBean(reviewBean.getUsername(), reviewCode) ;
        SendReviewCodeEmailBoundary.getInstance().sendCode(mailInformation) ;

        return reviewCode ;
    }

    public TutoringInformationBean getReviewFromCode(ReviewCodeBean reviewCode) throws TutorReviewException
    {
        String code = reviewCode.getReviewCode() ;
        ReviewEntity review = ReviewEntity.getFromDB(code) ;

        TutoringInformationBean tutoringInformationBean =  new TutoringInformationBean() ;

        tutoringInformationBean.setTutorsName(review.getTutorUsername());
        tutoringInformationBean.setTutoringSubject(review.getSubject());
        tutoringInformationBean.setTutoringDay(review.getDay()) ;
        tutoringInformationBean.setTutoringStart(review.getStartTime()) ;
        tutoringInformationBean.setTutoringEnd(review.getEndTime()) ;

        return tutoringInformationBean;
    }

    public void reviewTutor(ReviewTutorBean review) throws TutorReviewException
    {
        ReviewEntity.finalizeReview(review.getReviewStars() , review.getCode());
    }

}
