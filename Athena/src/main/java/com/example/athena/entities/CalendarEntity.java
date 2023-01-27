package com.example.athena.entities;

import com.example.athena.beans.EventBean;
import com.example.athena.beans.PresenceOfEventsBean;
import com.example.athena.exceptions.EventException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalendarEntity {

    private List<EventEntity> events;
    private HashMap<LocalDate, List<EventEntity>> map ;

    public CalendarEntity(YearMonth yearMonth) throws EventException {
        this.events = EventEntity.getEventsByYearMonth(yearMonth) ;
        this.map = new HashMap<>() ;

        for(EventEntity event : this.events)
        {
            LocalDate day = event.getDay() ;

            List<EventEntity> dailyEvents = map.getOrDefault(day, new ArrayList<>()) ;
            dailyEvents.add(event) ;
            map.put(day, dailyEvents) ;
        }
    }

    public List<EventBean> getEvents(LocalDate day) throws EventException
    {
        List<EventBean> dailyEvents = new ArrayList<>();
        for(EventEntity event : this.map.getOrDefault(day, new ArrayList<>())) {

            EventBean eventBean = new EventBean();
            eventBean.setDate(event.getDay());
            eventBean.setName(event.getName());
            eventBean.setStart(event.getStart());
            eventBean.setEnd(event.getEnd());
            eventBean.setDescription(event.getDescription());
            eventBean.setType(event.getType().toString()) ;
            if (event.getDateOfReminder() != null) {
                long period = event.getDateOfReminder().until(LocalDateTime.of(event.getDay(), event.getStart()), ChronoUnit.MINUTES) ;
                eventBean.setDateOfReminder((int) period / 60, (int) period % 60);
            }
            dailyEvents.add(eventBean);
        }
        return dailyEvents;
    }

    public PresenceOfEventsBean getEventsPresences()
    {
        PresenceOfEventsBean resultBean = new PresenceOfEventsBean() ;
        resultBean.setEventSet(this.map.keySet());
        return resultBean;
    }

    public void addEvent(EventEntity eventEntity){

        if (!this.events.contains(eventEntity)) this.events.add(eventEntity);

        List<EventEntity> list = this.map.getOrDefault(eventEntity.getDay(), new ArrayList<>()) ;
        if (!list.contains(eventEntity)) list.add(eventEntity) ;

        this.map.put(eventEntity.getDay(), list) ;
    }

    public void deleteEventEntity(EventEntity event){
        this.events.remove(event);
        List<EventEntity> list = this.map.getOrDefault(event.getDay(), new ArrayList<>()) ;
        list.remove(event);

        if (list.isEmpty()) this.map.remove(event.getDay()) ;
        else this.map.put(event.getDay(), list);
    }
}
