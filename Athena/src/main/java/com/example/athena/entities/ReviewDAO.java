package com.example.athena.entities;

import com.example.athena.exceptions.TutorReviewException;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReviewDAO extends AbstractDAO
{
    private String addReview = "INSERT INTO reviews(reviewCode, tutorUsername, studentUsername, tutoringDay, tutoringSubject, startTime, endTime) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)" ;
    private String getReview = "SELECT * FROM reviews WHERE reviewCode = ?" ;
    private String deleteReview = "DELETE FROM reviews WHERE reviewCode = ?" ;
    private String finalizeReview ="CALL athena.finalize(? , ?)" ;

    public void addReview(String reviewCode, String tutorUsername, String studentUsername, LocalDate tutoringDay, SubjectLabels tutoringSubject,
                          LocalTime startTime, LocalTime endTime) throws TutorReviewException
    {
        try (PreparedStatement statement = this.getConnection().prepareStatement(addReview))
        {
            statement.setString(1, reviewCode) ;
            statement.setString(2, tutorUsername) ;
            statement.setString(3, studentUsername) ;
            Date date = Date.valueOf(tutoringDay);
            statement.setString(4, date.toString()) ;
            statement.setString(5, tutoringSubject.toString()) ;
            Time sqlStartTime = Time.valueOf(startTime) ;
            statement.setString(6, sqlStartTime.toString()) ;
            Time sqlEndTime = Time.valueOf(endTime) ;
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
                if(!studentUsername.equals(Student.getInstance().getEmail())) throw new TutorReviewException("No information found") ;
                String reviewedTutor = resultTutoring.getString(2) ;
                Date reviewDay = resultTutoring.getDate(4) ;
                String reviewSubject = resultTutoring.getString(5) ;
                Time startTime = resultTutoring.getTime(6) ;
                Time endTime = resultTutoring.getTime(7) ;

                return new ReviewEntity(reviewCode, reviewedTutor, Student.getInstance().getEmail(), SubjectLabels.valueOf(reviewSubject),
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
    public void finalizee(String reviewCode , int reviewStars) throws TutorReviewException {

        try (PreparedStatement statement = this.getConnection().prepareStatement(finalizeReview)) {
            statement.setString(1 , reviewCode);
            statement.setInt(2 , reviewStars);
            statement.executeQuery() ;

        } catch (SQLException | IOException exc) {
            throw new TutorReviewException("Failed to connect to DB") ;
        }
    }
}
