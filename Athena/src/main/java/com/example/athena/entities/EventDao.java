package com.example.athena.entities;

import com.example.athena.exceptions.EventException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventDao extends AbstractDAO {

    private String email = User.getUser().getEmail();
    private String addQuery = "INSERT INTO athena.eventi (`dataEvento`, `eventName`, `eventStart`, `eventEnd`, `eventDesc`, `utente`, `type`) values (?,?,?,?,?,?,?)" ;
    private String getEventInfo = "select eventName , eventStart , eventEnd , eventDesc , dataEvento, type from athena.eventi where dataEvento = ? and utente = ? " ;

    private String getEventsByTypeSpan = "SELECT eventName, eventStart, eventEnd, eventDesc, dataEvento " +
                                         "FROM athena.eventi " +
                                         "WHERE utente = ? AND type = ? AND dataEvento >= ?" ;


    public void addEvent(LocalDate data , String name , LocalTime start ,LocalTime end , String description , String type) {

        try (PreparedStatement statement = this.getConnection().prepareStatement(addQuery)) {

            statement.setDate(1, Date.valueOf(data));
            statement.setString(2,name);
            statement.setTime(3, Time.valueOf(start));
            statement.setTime(4, Time.valueOf(end));
            statement.setString(5, description);
            statement.setString(6 , email);
            statement.setString(7, type);

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
                eventinfos[i+5] = set.getString(6) ;
                i = i + 6 ;


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

    public void updateEvento(LocalDate data , String name , LocalTime start ,LocalTime end , String description , String oldname, String type) {
        try (PreparedStatement statement = this.getConnection().prepareStatement("update athena.eventi set dataEvento = ? , eventName = ? , eventStart = ? , eventEnd = ? , eventDesc = ? , type = ? where utente = ? and dataEvento = ? and eventName = ?")){
            statement.setDate(1, Date.valueOf(data));
            statement.setString(2,name);
            statement.setTime(3, Time.valueOf(start));
            statement.setTime(4, Time.valueOf(end));
            statement.setString(5,description);
            statement.setString(6, type) ;
            statement.setString(7,email) ;
            statement.setDate(8, Date.valueOf(data)) ;
            statement.setString(9,oldname) ;

            statement.execute();
        }catch (SQLException exc ){
            exc.getMessage();
        }
    }

    public List<EventEntity> getEntitiesByTypeSpan(ActivityTypesEnum type, LocalDate timeSpan) throws EventException
    {
        try(PreparedStatement statement = this.getConnection().prepareStatement(getEventsByTypeSpan))
        {
            ArrayList<EventEntity> events = new ArrayList<>() ;
            Date start = Date.valueOf(timeSpan) ;

            statement.setString(1, User.getUser().getEmail()) ;
            statement.setString(2, type.toString()) ;
            statement.setDate(3, start) ;

            ResultSet results = statement.executeQuery() ;

            while(results.next())
            {
                String eventName = results.getString(1) ;
                LocalTime startTime = results.getTime(2).toLocalTime() ;
                LocalTime endTime = results.getTime(3).toLocalTime() ;
                String eventDescription = results.getString(4) ;
                LocalDate eventDay = results.getDate(5).toLocalDate() ;

                events.add(new EventEntity(eventName, eventDay, startTime, endTime, eventDescription, type)) ;
            }

            return events ;
        }catch(SQLException e)
        {
            throw new EventException("Error in retrieving entities, details follow: " + e.getMessage()) ;
        }
    }
}
