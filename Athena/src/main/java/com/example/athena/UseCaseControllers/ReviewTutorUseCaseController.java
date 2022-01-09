package com.example.athena.UseCaseControllers;

import com.example.athena.Exceptions.TutorReviewException;
import com.example.athena.GraphicalController.ReviewCodeBean;
import com.example.athena.GraphicalController.ReviewTutorSendUsernameBean;
import com.example.athena.GraphicalController.SendReviewBean;
import com.example.athena.GraphicalController.TutoringInformationBean;
import com.example.athena.View.*;

import java.time.LocalDate;

public class ReviewTutorUseCaseController
{
    public String generateReview(ReviewTutorSendUsernameBean usernameBean) throws TutorReviewException
    {
        String studentUsername = usernameBean.getUsername() ;
        SubjectLabels subject = usernameBean.getSubject() ;
        LocalDate day = usernameBean.getDay() ;
        int startHour = usernameBean.getStartHour() ;
        int startMinute = usernameBean.getStartMinute() ;
        int endHour = usernameBean.getEndHour() ;
        int endMinute = usernameBean.getEndMinute() ;

        String reviewCode = TutorReviewCodesGenerator.generateReviewCode(5) ;

        ReviewEntity review = new ReviewEntity(reviewCode, user.getUser().getEmail(), studentUsername, subject, day,
                startHour, startMinute, endHour, endMinute) ;
        review.toDB() ;

        return reviewCode ;
    }

    public TutoringInformationBean reviewTutor(ReviewCodeBean reviewCode) throws TutorReviewException
    {
        String code = reviewCode.getReviewCode() ;
        ReviewEntity review = ReviewEntity.getFromDB(code) ;
        LastCodeRememberer.writeLastCode(code) ;

        return new TutoringInformationBean(review.getTutorUsername(), review.getSubject().toString(), review.getDay(),
                review.getStartTime(), review.getEndTime()) ;
    }

    public void sendReview(SendReviewBean review) throws TutorReviewException
    {

        ReviewEntity.removeFromDB(LastCodeRememberer.getLastCode()) ;
    }
}
