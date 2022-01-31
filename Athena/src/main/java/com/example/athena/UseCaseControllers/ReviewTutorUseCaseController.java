package com.example.athena.UseCaseControllers;

import com.example.athena.Boundaries.SendReviewCodeEmailBoundary;
import com.example.athena.Entities.ReviewEntity;
import com.example.athena.Entities.SubjectLabels;
import com.example.athena.Entities.TutorReviewCodesGenerator;
import com.example.athena.Entities.user;
import com.example.athena.Exceptions.SendEmailException;
import com.example.athena.Exceptions.TutorReviewException;
import com.example.athena.GraphicalController.ReviewCodeBean;
import com.example.athena.GraphicalController.ReviewTutorSendUsernameBean;
import com.example.athena.GraphicalController.SendReviewBean;
import com.example.athena.GraphicalController.TutoringInformationBean;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

public class ReviewTutorUseCaseController
{
    public String generateReview(ReviewTutorSendUsernameBean usernameBean) throws TutorReviewException, SendEmailException
    {
        String studentUsername = usernameBean.getUsername() ;


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
