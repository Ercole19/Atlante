package com.example.athena.entities;

import com.example.athena.exceptions.EventException;
import com.example.athena.graphical_controller.EventBean;
import com.example.athena.graphical_controller.PresenceOfEventsBean;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

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
            if (event.getDateOfReminder() != null) eventBean.setDateOfReminder(event.getDateOfReminder().toLocalDateTime().getHour(), event.getDateOfReminder().toLocalDateTime().getMinute());
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
        this.events.add(eventEntity);
        List<EventEntity> list = this.map.getOrDefault(eventEntity.getDay(), new ArrayList<>()) ;
        list.add(eventEntity) ;
        this.map.put(eventEntity.getDay(), list) ;
    }

    public void deleteEventEntity(EventEntity event){
        this.events.remove(event);
        List<EventEntity> list = this.map.getOrDefault(event.getDay(), new ArrayList<>()) ;
        list.remove(event);
        this.map.put(event.getDay(), list);
    }
}
