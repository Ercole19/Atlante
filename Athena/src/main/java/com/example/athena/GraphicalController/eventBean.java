package com.example.athena.GraphicalController;

import java.time.LocalDate;
import java.time.LocalTime;

public class eventBean {



    private LocalDate date ;
    private String name ;
    private LocalTime start ;
    private LocalTime end ;
    private String description ;

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart(int hours , int minutes) {
        LocalTime inizio = LocalTime.of(hours, minutes);
        this.start = inizio ;
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
}
