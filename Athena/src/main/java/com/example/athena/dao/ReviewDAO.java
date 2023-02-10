package com.example.athena.dao;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.entities.ReviewEntity;
import com.example.athena.exceptions.TutorReviewException;

import java.io.IOException;
import java.sql.*;

public class ReviewDAO extends AbstractDAO
{
    private String addReview = "INSERT INTO reviews(reviewCode, tutorUsername, studentUsername, tutoringDay, tutoringSubject, startTime, endTime) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)" ;
    private String getReview = "SELECT * FROM reviews WHERE reviewCode = ?" ;
    private String deleteReview = "DELETE FROM reviews WHERE reviewCode = ?" ;

    public void addReview(ReviewEntity entity) throws TutorReviewException
    {
        try (PreparedStatement statement = this.getConnection().prepareStatement(addReview))
        {
            statement.setString(1, entity.getReviewCode()) ;
            statement.setString(2, entity.getTutorUsername()) ;
            statement.setString(3, entity.getStudentUsername()) ;
            Date date = Date.valueOf(entity.getDay());
            statement.setString(4, date.toString()) ;
            statement.setString(5, entity.getSubject());
            Time sqlStartTime = Time.valueOf(entity.getStartTime()) ;
            statement.setString(6, sqlStartTime.toString()) ;
            Time sqlEndTime = Time.valueOf(entity.getEndTime()) ;
            statement.setString(7, sqlEndTime.toString()) ;
            statement.executeUpdate() ;
        }catch (SQLException | IOException e)
        {
            throw new TutorReviewException("Error in loading the information") ;
        }
    }

    public ReviewEntity getReview(String reviewCode) throws TutorReviewException
    {
        try (PreparedStatement statement = this.getConnection().prepareStatement(getReview))
        {
            statement.setString(1, reviewCode) ;
            ResultSet resultTutoring = statement.executeQuery() ;

            if(resultTutoring.next())
            {
                String studentUsername = resultTutoring.getString(3) ;
                if(!studentUsername.equals(LoggedStudent.getInstance().getEmail().getMail())) throw new TutorReviewException("No information found") ;
                String reviewedTutor = resultTutoring.getString(2) ;
                Date reviewDay = resultTutoring.getDate(4) ;
                String reviewSubject = resultTutoring.getString(5) ;
                Time startTime = resultTutoring.getTime(6) ;
                Time endTime = resultTutoring.getTime(7) ;

                return new ReviewEntity(reviewCode, reviewedTutor, LoggedStudent.getInstance().getEmail().getMail(), reviewSubject,
                        reviewDay.toLocalDate(), startTime.toLocalTime(), endTime.toLocalTime()) ;
            }
            else throw new TutorReviewException("No information found") ;
        }catch (SQLException | IOException e)
        {
            throw new TutorReviewException("Failed to retrieve from DB") ;
        }
    }

    public void deleteReview(String reviewCode) throws TutorReviewException
    {
        try (PreparedStatement statement = this.getConnection().prepareStatement(deleteReview))
        {
            statement.setString(1, reviewCode) ;
            statement.executeUpdate() ;
        }catch (SQLException | IOException e)
        {
            throw new TutorReviewException("Failed to remove from DB") ;
        }
    }
}
