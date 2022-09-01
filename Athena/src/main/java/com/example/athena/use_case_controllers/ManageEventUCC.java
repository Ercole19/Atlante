package com.example.athena.use_case_controllers;


import com.example.athena.boundaries.SetReminderEmailBoundary;
import com.example.athena.entities.ActivityTypesEnum;
import com.example.athena.entities.CalendarSubject;
import com.example.athena.entities.EventDao;
import com.example.athena.entities.EventEntity;
import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.graphical_controller.EventBean;

public class ManageEventUCC {
    private EventEntity eventEntity;
    
    public void addEvent(EventBean event) throws SendEmailException, EventException {
        if (event.isThereAReminder())  eventEntity = new EventEntity(event.getName(), event.getDate(), event.getStart(), event.getEnd(), event.getDescription(), ActivityTypesEnum.valueOf(event.getType()), event.getDateOfReminder());
        else eventEntity = new EventEntity(event.getName(), event.getDate(), event.getStart(), event.getEnd(), event.getDescription(), ActivityTypesEnum.valueOf(event.getType()));
        eventEntity.addEntity();
        CalendarSubject.getInstance().addEvent(eventEntity);
        if(event.isThereAReminder()) {
            SetReminderEmailBoundary.sendToServer(event, false) ;
        }
    }

    public void update(EventBean event, EventBean oldEvent) throws SendEmailException, EventException {
        deleteEvent(oldEvent);
        addEvent(event);
    }

    public void deleteEvent (EventBean event) throws SendEmailException, EventException {
        if (event.isThereAReminder())  eventEntity = new EventEntity(event.getName(), event.getDate(), event.getStart(), event.getEnd(), event.getDescription(), ActivityTypesEnum.valueOf(event.getType()), event.getDateOfReminder());
        else eventEntity = new EventEntity(event.getName(), event.getDate(), event.getStart(), event.getEnd(), event.getDescription(), ActivityTypesEnum.valueOf(event.getType()));
        eventEntity.deleteEntity();
        CalendarSubject.getInstance().deleteEvent(eventEntity);
        if(event.isThereAReminder()) SetReminderEmailBoundary.sendToServer(event, true) ;
    }
}
