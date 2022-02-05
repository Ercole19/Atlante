package com.example.athena.entities;

import com.example.athena.exceptions.EventException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

public class EventEntity
{
    private String name ;
    private LocalDate day ;
    private LocalTime start ;
    private LocalTime end ;
    private String description ;
    private ActivityTypesEnum type ;

    public EventEntity(String name, LocalDate day, LocalTime start, LocalTime end, String description, ActivityTypesEnum type)
    {
        this.setName(name) ;
        this.setDay(day) ;
        this.setStart(start) ;
        this.setEnd(end) ;
        this.setDescription(description);
        this.setType(type) ;
    }

    public static List<EventEntity> getEventsByTypeSpan(ActivityTypesEnum type, TimePeriodsEnum timeSpan) throws EventException
    {
        EventDao dao = new EventDao() ;

        LocalDate start = LocalDate.MIN ;

        switch(timeSpan)
        {
            case LAST_WEEK:
                start = LocalDate.now().minusWeeks(1) ;
                break ;
            case LAST_MONTH:
                start = LocalDate.now().minusMonths(1) ;
                break ;
            case LAST_TWO_WEEKS:
                start = LocalDate.now().minusWeeks(2) ;
                break ;
            case FROM_BEGINNING:
                break ;
        }

        return dao.getEntitiesByTypeSpan(type, start) ;
    }

    public long getSpanMinutes()
    {
        return Long.divideUnsigned(this.end.toEpochSecond(this.getDay(), ZoneOffset.UTC) - this.start.toEpochSecond(this.getDay(), ZoneOffset.UTC), 60) ;
    }

    public void setName(String name)
    {
        this.name = name ;
    }

    public void setDay(LocalDate day)
    {
        this.day = day ;
    }

    public void setStart(LocalTime start)
    {
        this.start = start ;
    }

    public void setEnd(LocalTime end)
    {
        this.end = end ;
    }

    public void setDescription(String description)
    {
        this.description = description ;
    }

    public void setType(ActivityTypesEnum type)
    {
        this.type = type ;
    }

    public String getName()
    {
        return this.name ;
    }

    public LocalDate getDay()
    {
        return this.day ;
    }

    public LocalTime getStart()
    {
        return this.start ;
    }

    public LocalTime getEnd()
    {
        return this.end ;
    }

    public String getDescription()
    {
        return this.description ;
    }
}
