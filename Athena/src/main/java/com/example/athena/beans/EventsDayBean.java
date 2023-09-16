package com.example.athena.beans;

import java.time.LocalDate;

public class EventsDayBean {
    private LocalDate date ;

    public EventsDayBean(LocalDate date) {
        this.date = date ;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
