package com.example.athena.beans;

import com.example.athena.exceptions.EventException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventBean {

    protected LocalDate date ;
    protected String name ;
    protected LocalTime start ;
    protected LocalTime end ;
    protected String description ;
    protected String type ;

    protected LocalDateTime dateOfReminder ;



    public void setDateOfReminder(int hoursBefore, int minutesBefore)
    {
        if (hoursBefore == 0 && minutesBefore == 0) this.dateOfReminder = null ;
        else this.dateOfReminder = LocalDateTime.of(this.getDate(), this.getStart()).minusHours(hoursBefore).minusMinutes(minutesBefore) ;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setName(String name) throws EventException {
        if (name.equals(""))
        {
          throw new EventException("Add a name to the event");
        }
        else{ this.name = name;}

    }

    public void setType(String type)
    {
        this.type = type ;
    }

    public void setStart(LocalTime start) {
        this.start = start ;
    }


    public void setEnd(LocalTime end) {
        this.end = end ;
    }

    public void setDescription(String description)throws EventException {
        if(description.length() > 50)
        {
           throw new EventException("Description is too long.");
        }
        else{ this.description = description;}
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public String getDescription() {
        return description;
    }

    public String getType()
    {
        return this.type ;
    }


    public LocalDateTime getDateOfReminder()throws EventException
    {
        if(!this.isThereAReminder()) throw new EventException("Reminder couldn't be fetched because it doesn't exist");
        return this.dateOfReminder ;
    }
    
    public boolean isThereAReminder()
    {
        return this.dateOfReminder != null ;
    }
}
