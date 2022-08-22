package com.example.athena.entities;

import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import com.example.athena.exceptions.EventException;
import com.example.athena.graphical_controller.PresenceOfEventsBean;

import java.time.YearMonth;
import java.util.HashMap;

public class CalendarSubject extends AbstractSubject {

    private static CalendarSubject instance = null ;
    private HashMap<YearMonth, CalendarEntity> calendarMap;

    private CalendarSubject()
    {
        this.calendarMap = new HashMap<>() ;
    }

    public static synchronized CalendarSubject getInstance()
    {
        if(CalendarSubject.instance == null)
        {
            CalendarSubject.instance = new CalendarSubject() ;
        }

        return CalendarSubject.instance ;
    }

    public CalendarEntity getEntity(YearMonth yearMonth) throws EventException {

        CalendarEntity retVal = this.calendarMap.get(yearMonth) ;
        if (retVal == null) {
            this.calendarMap.put(yearMonth,new CalendarEntity(yearMonth)) ;
            retVal = this.calendarMap.get(yearMonth) ;
        }
        return retVal ;
    }

    public void addEvent(EventEntity event) throws EventException
    {
        this.getEntity(YearMonth.of(event.getDay().getYear(), event.getDay().getMonth())).addEvent(event) ;
        this.calendarMap.put(YearMonth.of(event.getDay().getYear(), event.getDay().getMonth()),  this.getEntity(YearMonth.of(event.getDay().getYear(), event.getDay().getMonth())));
        super.notifyObserver();
    }

    public void deleteEvent(EventEntity event) throws EventException {
        this.getEntity(YearMonth.of(event.getDay().getYear(), event.getDay().getMonth())).deleteEventEntity(event);
        this.calendarMap.remove(YearMonth.of(event.getDay().getYear(), event.getDay().getMonth()),  this.getEntity(YearMonth.of(event.getDay().getYear(), event.getDay().getMonth())));
        super.notifyObserver();
    }

    public PresenceOfEventsBean getEventPresencesByYearMonth(YearMonth yearMonth) throws EventException
    {
        return this.getEntity(yearMonth).getEventsPresences() ;
    }

    public void refreshOnLogOut() {
        this.calendarMap.clear() ;
    }
}
