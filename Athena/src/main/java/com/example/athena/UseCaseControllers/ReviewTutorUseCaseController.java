package com.example.athena.UseCaseControllers;

import com.example.athena.GraphicalController.ReviewTutorSendUsernameBean;
import com.example.athena.View.ReviewEntity;
import com.example.athena.View.SubjectLabels;
import com.example.athena.View.TutorReviewCodesGenerator;

import java.time.LocalDate;

public class ReviewTutorUseCaseController
{
    public String generateReview(ReviewTutorSendUsernameBean usernameBean)
    {
        String username = usernameBean.getUsername() ;
        SubjectLabels subject = usernameBean.getSubject() ;
        LocalDate day = usernameBean.getDay() ;
        int startHour = usernameBean.getStartHour() ;
        int startMinute = usernameBean.getStartMinute() ;
        int endHour = usernameBean.getEndHour() ;
        int endMinute = usernameBean.getEndMinute() ;

        String reviewCode = TutorReviewCodesGenerator.generateReviewCode(5) ;

        ReviewEntity review = new ReviewEntity(reviewCode, username, subject, day, startHour, endHour, startMinute, endMinute) ;
        review.toDB() ;

        return reviewCode ;
    }
}
