package com.example.athena.View;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class eventDao extends AbstractDAO {

    private String user = "test";
    private String Password = "test";
    private String dbUrl = "jdbc:mysql://78.13.194.135/athena";
    private String email = com.example.athena.View.user.getUser().getEmail();
    private String addQuery = "INSERT INTO athena.events (`dataEvento`, `eventName`, `eventStart`, `eventEnd`, `eventDesc`, `user`) values (?,?,?,?,?,?)" ;
    private String getEventInfo = "select eventName , eventStart , eventEnd , eventDesc from athena.events where dataEvento = ? and user = ? " ;

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

    public String[] findeventinfo(String data) {
        String[] eventinfos = new String[500];
        int i = 0;
        try (PreparedStatement statement = this.getConnection().prepareStatement(getEventInfo)) {
            statement.setString(1, data);
            statement.setString(2, email);
            //declare with size
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                eventinfos[i] = set.getString(1);
                eventinfos[i + 1] = String.valueOf(set.getTime(2));
                eventinfos[i + 2] = String.valueOf(set.getTime(3));
                eventinfos[i + 3] = set.getString(4) ;
                i = i + 4 ;


            }

        } catch (SQLException exc) {
            exc.getMessage();
        }

        return eventinfos ;

    }


}
