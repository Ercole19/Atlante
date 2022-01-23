package com.example.athena.View;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class eventDao extends AbstractDAO {

    private String user = "test";
    private String Password = "test";
    private String dbUrl = "jdbc:mysql://78.13.194.135/athena";
    private String email = com.example.athena.View.user.getUser().getEmail();
    private String addQuery = "INSERT INTO athena.events (`dataEvento`, `eventName`, `eventStart`, `eventEnd`, `eventDesc`, `user`) values (?,?,?,?,?,?)" ;


    public void addEvent(LocalDate data , String name , LocalTime start ,LocalTime end , String description) {

        try (PreparedStatement statement = this.getConnection().prepareStatement(addQuery)) {

            statement.setDate(1, Date.valueOf(data));
            statement.setString(2,name);
            statement.setTime(3, Time.valueOf(start));
            statement.setTime(4, Time.valueOf(end));
            statement.setString(5, description);
            statement.setString(6 , email);

            statement.executeUpdate() ;

        } catch (SQLException exc) {
            System.out.println(exc.getMessage()) ;
        }

    }


}
