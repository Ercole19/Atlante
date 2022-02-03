package com.example.athena.graphical_controller;

import com.example.athena.entities.ActivityTypesEnum;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventBean {



    private LocalDate date ;
    private String name ;
    private LocalTime start ;
    private LocalTime end ;
    private String description ;
    private String type ;

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

    public void setStart(int hours , int minutes) {
        LocalTime inizio = LocalTime.of(hours, minutes);
        this.start = inizio ;
    }

    public void setStartbyString (String start) {
        LocalTime inizio = LocalTime.parse(start) ;
        this.start = inizio ;
    }


    public void setEndbyString (String end) {
        LocalTime fine = LocalTime.parse(end) ;
        this.end = fine ;
    }


    public void setEnd(int hours , int minutes) {
        LocalTime fine = LocalTime.of(hours, minutes);
        this.end = fine ;
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
}
