package com.example.athena.entities;

import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import com.example.athena.exceptions.EventException;

import java.time.YearMonth;
import java.util.HashMap;

public class CalendarSubject extends AbstractSubject {

    private static CalendarSubject instance = null ;
    private HashMap<YearMonth, CalendarEntity> calendarMap;

    private CalendarSubject()
    {

    }

    public static synchronized CalendarSubject getInstance()
    {
        if(CalendarSubject.instance == null)
        {
            this.instance = new CalendarSubject() ;
        }

        return CalendarSubject.instance ;
    }

    public CalendarEntity getEntity(YearMonth yearMonth) throws EventException {

        this.calendarMap.putIfAbsent(yearMonth, new CalendarEntity(yearMonth)) ;
        return this.calendarMap.get(yearMonth) ;
    }

    public void addEvent(EventEntity event) throws EventException
    {
        this.getEntity(YearMonth.of(event.getDay().getYear(), event.getDay().getMonth())).addEvent(event) ;
    }
}
