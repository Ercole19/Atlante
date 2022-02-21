package com.example.athena.entities;

import com.example.athena.exceptions.EventException;
import com.example.athena.graphical_controller.EventBean;
import com.example.athena.graphical_controller.PresenceOfEventsBean;

import java.time.LocalDate;
import java.time.YearMonth;
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

    public List<EventBean> getEvents(LocalDate day)
    {
        List<EventBean> events = new ArrayList<>();
        for(EventEntity event : this.map.getOrDefault(day, new ArrayList<EventEntity>())) {

            EventBean eventBean = new EventBean();
            eventBean.setDate(event.getDay());
            eventBean.setName(event.getName());
            eventBean.setStart(event.getStart());
            eventBean.setEnd(event.getEnd());
            eventBean.setDescription(event.getDescription());
            eventBean.setType(event.getType().toString()) ;

            events.add(eventBean);


        }
        return events;
    }

    public PresenceOfEventsBean isThereAnEventOnThatDay(LocalDate day)
    {
        return new PresenceOfEventsBean(this.map.containsKey(day)) ;
    }
}