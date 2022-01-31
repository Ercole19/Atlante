package com.example.athena.use_case_controllers;

import com.example.athena.entities.ReviewEntity;
import com.example.athena.entities.TutorReviewCodesGenerator;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.TutorReviewException;
import com.example.athena.graphical_controller.ReviewCodeBean;
import com.example.athena.graphical_controller.ReviewTutorSendUsernameBean;
import com.example.athena.graphical_controller.SendReviewBean;
import com.example.athena.graphical_controller.TutoringInformationBean;

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

        //SendReviewCodeEmailBoundary.sendEmail(studentUsername, reviewCode) ;

        return reviewCode ;
    }

    public TutoringInformationBean reviewTutor(ReviewCodeBean reviewCode) throws TutorReviewException
    {
        String code = reviewCode.getReviewCode() ;
        ReviewEntity review = ReviewEntity.getFromDB(code) ;

        return new TutoringInformationBean(review.getTutorUsername(), review.getSubject().toString(), review.getDay(),
                review.getStartTime(), review.getEndTime()) ;
    }

    public void sendReview(SendReviewBean review) throws TutorReviewException
    {

        ReviewEntity.finalizeReview(review.getReviewStars() , review.getCode());
    }
}
