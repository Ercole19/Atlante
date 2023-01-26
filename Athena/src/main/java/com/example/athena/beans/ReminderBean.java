package com.example.athena.beans;

import com.example.athena.exceptions.EventException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReminderBean {

    private LocalDate date ;
    private String name ;
    private LocalTime start ;
    private LocalTime end ;
    private String description ;
    private String type ;

    private Timestamp dateOfReminder ;

    private boolean remove;

    public ReminderBean(EventBean bean, boolean remove) throws EventException {
        this.name = bean.getName();
        this.start = bean.getStart() ;
        this.end = bean.getEnd() ;
        this.date = bean.getDate() ;
        this.type = bean.getType() ;
        this.description = bean.getDescription() ;
        this.dateOfReminder = bean.getDateOfReminder() ;
        this.remove = remove ;
    }

    public void setDateOfReminder(int hoursBefore, int minutesBefore)
    {
        if (hoursBefore == 0 && minutesBefore == 0) this.dateOfReminder = null ;
        else this.dateOfReminder = Timestamp.valueOf(LocalDateTime.of(this.getDate(), this.getStart()).minusHours(hoursBefore).minusMinutes(minutesBefore));
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


    public Timestamp getDateOfReminder()throws EventException
    {
        if(!this.isThereAReminder()) throw new EventException("Reminder couldn't be fetched because it doesn't exist");
        return this.dateOfReminder ;
    }

    public boolean isThereAReminder()
    {
        return this.dateOfReminder != null ;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }
}
