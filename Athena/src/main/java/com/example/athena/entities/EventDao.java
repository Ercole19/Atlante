package com.example.athena.entities;

import com.example.athena.exceptions.EventException;

import java.sql.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class EventDao extends AbstractDAO {
    private final String email = Student.getInstance().getEmail();

    public void addEvent(LocalDate date , String name , LocalTime start ,LocalTime end , String description , String type) throws EventException {

        try (PreparedStatement statement = this.getConnection().prepareStatement("INSERT INTO athena.eventi (`dataEvento`, `eventName`, `eventStart`, `eventEnd`, `eventDesc`, `utente`, `type`) values (?,?,?,?,?,?,?)")) {

            statement.setDate(1, Date.valueOf(date));
            statement.setString(2,name);
            statement.setTime(3, Time.valueOf(start));
            statement.setTime(4, Time.valueOf(end));
            statement.setString(5, description);
            statement.setString(6 , email);
            statement.setString(7, type);

            statement.executeUpdate() ;


        } catch (SQLException exc) {
            throw new EventException("Error in adding new event, details follow: " + exc.getMessage());
        }

    }



    public void delete(String nome , LocalDate date) throws EventException {
        try (PreparedStatement statement = this.getConnection().prepareStatement("call athena.delete_event(?,? ,?)" )) {

            statement.setString(1,nome);
            statement.setString(2,email);
            statement.setDate(3, Date.valueOf(date));

            statement.execute();


        }catch (SQLException exc) {
            throw new EventException("Error in deleting event, details follow: " + exc.getMessage());
        }
    }

    public void updateEvent(LocalDate data , String name , LocalTime start , LocalTime end , String description , String oldname, String type) throws EventException {
        try (PreparedStatement statement = this.getConnection().prepareStatement("update athena.eventi set dataEvento = ? , eventName = ? , eventStart = ? , eventEnd = ? , eventDesc = ? , type = ? where utente = ? and dataEvento = ? and eventName = ?"))
        {
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
        }catch (SQLException exc)
        {
            throw new EventException("Error in updating event, details follow: " + exc.getMessage()) ;
        }
    }

    public List<EventEntity> getEntitiesByTypeSpan(ActivityTypesEnum type, LocalDate timeSpan) throws EventException
    {
        try(PreparedStatement statement = this.getConnection().prepareStatement("SELECT eventName, eventStart, eventEnd, eventDesc, dataEvento FROM athena.eventi WHERE utente = ? AND type = ? AND dataEvento >= ? ORDER BY dataEvento" ))
        {
            ArrayList<EventEntity> events = new ArrayList<>() ;
            Date start = Date.valueOf(timeSpan) ;

            statement.setString(1, Student.getInstance().getEmail()) ;
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

    public List<EventEntity> getEventsByYearMonth(YearMonth yearMonth) throws EventException{
        List<EventEntity> events = new ArrayList<>();
        try(PreparedStatement statement = this.getConnection().prepareStatement("SELECT * from athena.eventi where DATE_FORMAT(dataEvento, '%Y-%m') = ? and utente = ?")){

            statement.setString(1, String.valueOf(yearMonth));
            statement.setString(2, Student.getInstance().getEmail());
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                LocalDate eventDay = set.getDate(1).toLocalDate() ;
                String eventName = set.getString(2) ;
                LocalTime startTime = set.getTime(3).toLocalTime() ;
                LocalTime endTime = set.getTime(4).toLocalTime() ;
                String eventDescription = set.getString(5) ;
                ActivityTypesEnum type = ActivityTypesEnum.valueOf(set.getString(7));

                events.add(new EventEntity(eventName, eventDay, startTime, endTime, eventDescription, type)) ;
            }

        }
        catch(SQLException e)
        {
            throw new EventException("Error in retrieving events, details follow: " + e.getMessage()) ;
        }
        return events ;
    }


}
