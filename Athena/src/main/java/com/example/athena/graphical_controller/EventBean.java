package com.example.athena.graphical_controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventBean {

    private LocalDate date ;
    private String name ;
    private LocalTime start ;
    private LocalTime end ;
    private String description ;
    private String type ;

    private boolean isThereAReminder ;
    private LocalDateTime dateOfReminder ;

    public EventBean()
    {

    }

    public EventBean(LocalDate date, String name, LocalTime start, LocalTime end, String description, String type, EventReminderWrapperBean wrapperBean)
    {
        setDate(date) ;
        setName(name) ;
        setStart(start);
        setEnd(end) ;
        setDescription(description) ;
        setType(type) ;
        setIsThereAReminder(wrapperBean.getIsThereAReminder()) ;
        if(isThereAReminder) setDateOfReminder(wrapperBean.getHoursBefore(), wrapperBean.getMinutesBefore()) ;
    }

    public void setIsThereAReminder(boolean value)
    {
        this.isThereAReminder = value ;
    }

    private void setDateOfReminder(int hoursBefore, int minutesBefore)
    {
        this.dateOfReminder = LocalDateTime.of(this.getDate(), this.getStart()).minusHours(hoursBefore).minusMinutes(minutesBefore) ;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type)
    {
        this.type = type.toUpperCase().replace(" ", "_") ;
    }

    public void setStart(LocalTime start) {
        this.start = start ;
    }

    public void setStartbyString (String start) {
        LocalTime inizio = LocalTime.parse(start) ;
        this.start = inizio ;
    }


    public void setEndbyString (String end) {
        LocalTime fine = LocalTime.parse(end) ;
        this.end = fine ;
    }


    public void setEnd(LocalTime end) {
        this.end = end ;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean getIsThereAReminder()
    {
        return this.isThereAReminder ;
    }

    public LocalDateTime getDateOfReminder()
    {
        return this.dateOfReminder ;
    }
}
