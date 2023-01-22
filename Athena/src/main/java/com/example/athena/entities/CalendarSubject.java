package com.example.athena.entities;

import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import com.example.athena.exceptions.EventException;
import com.example.athena.beans.normal.PresenceOfEventsBean;
import com.example.athena.exceptions.SizedAlert;
import javafx.scene.control.Alert;

import java.time.YearMonth;
import java.util.HashMap;

public class CalendarSubject extends AbstractSubject {

    private static CalendarSubject instance = null ;
    private final HashMap<YearMonth, CalendarEntity> calendarMap;

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

    public CalendarEntity getEntity(YearMonth yearMonth) throws EventException{

        return this.calendarMap.computeIfAbsent(yearMonth, k -> {CalendarEntity calendar = null;
            try{calendar = new CalendarEntity(k);}
            catch (EventException e){
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800, 600);
                alert.showAndWait();
            }
            return calendar;}) ;
        
    }

    public void addEvent(EventEntity event) throws EventException
    {
        this.getEntity(YearMonth.of(event.getDay().getYear(), event.getDay().getMonth())).addEvent(event) ;
        this.calendarMap.put(YearMonth.of(event.getDay().getYear(), event.getDay().getMonth()),  this.getEntity(YearMonth.of(event.getDay().getYear(), event.getDay().getMonth())));
        super.notifyObserver();
    }

    public void deleteEvent(EventEntity event) throws EventException {
        this.getEntity(YearMonth.of(event.getDay().getYear(), event.getDay().getMonth())).deleteEventEntity(event);
        this.calendarMap.put(YearMonth.of(event.getDay().getYear(), event.getDay().getMonth()),  this.getEntity(YearMonth.of(event.getDay().getYear(), event.getDay().getMonth())));
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
