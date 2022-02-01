package com.example.athena.entities;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventDao extends AbstractDAO {

    private String email = User.getUser().getEmail();
    private String addQuery = "INSERT INTO athena.eventi (`dataEvento`, `eventName`, `eventStart`, `eventEnd`, `eventDesc`, `utente`) values (?,?,?,?,?,?)" ;
    private String getEventInfo = "select eventName , eventStart , eventEnd , eventDesc , dataEvento from athena.eventi where dataEvento = ? and utente = ? " ;

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
            exc.getMessage() ;
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
                eventinfos[i+4] = String.valueOf(set.getDate(5));
                i = i + 5 ;


            }

        } catch (SQLException exc) {
            exc.getMessage();
        }

        return eventinfos ;

    }

    public void delete(String nome , LocalDate date) {
        try (PreparedStatement statement = this.getConnection().prepareStatement("call athena.delete_event(?,? ,?)" )) {

            statement.setString(1,nome);
            statement.setString(2,email);
            statement.setDate(3, Date.valueOf(date));

            statement.execute();


        }catch (SQLException exc) {
            exc.getMessage();
        }
    }


}
