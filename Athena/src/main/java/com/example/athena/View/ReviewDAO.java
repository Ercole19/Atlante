package com.example.athena.View;

import java.sql.* ;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalField;

public class ReviewDAO
{
    private String user = "test" ;
    private String pass = "test" ;
    private String dbUrl = "jdbc:mysql://78.13.228.115:3306/athena" ;

    private String addReview = "INSERT INTO reviews(reviewCode, tutorUsername, studentUsername, tutoringDay, tutoringSubject, startTime, endTime) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)" ;
    private String getReview = "SELECT * FROM reviews WHERE reviewCode = ?" ;
    private String deleteReview = "DELETE FROM reviews WHERE reviewCode = ?" ;

    public void addReview(String reviewCode, String tutorUsername, String studentUsername, LocalDate tutoringDay, SubjectLabels tutoringSubject,
                          LocalTime startTime, LocalTime endTime)
    {
        try (Connection connection = DriverManager.getConnection(dbUrl, user, pass); PreparedStatement statement = connection.prepareStatement(addReview))
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
        }catch (SQLException e)
        {
            e.printStackTrace() ;
        }
    }

    public void getReview(String reviewCode)
    {
        try (Connection connection = DriverManager.getConnection(dbUrl, user, pass); PreparedStatement statement = connection.prepareStatement(getReview))
        {
            statement.setString(1, reviewCode) ;
        }catch (SQLException e)
        {
            e.printStackTrace() ;
        }
    }

    public void deleteReview(String reviewCode)
    {
        try (Connection connection = DriverManager.getConnection(dbUrl, user, pass); PreparedStatement statement = connection.prepareStatement(deleteReview))
        {
            statement.setString(1, reviewCode) ;
        }catch (SQLException e)
        {
            e.printStackTrace() ;
        }
    }
}
