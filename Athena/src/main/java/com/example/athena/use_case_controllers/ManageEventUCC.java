package com.example.athena.use_case_controllers;


import com.example.athena.boundaries.SetReminderEmailBoundary;
import com.example.athena.entities.EventDao;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.graphical_controller.EventBean;

public class ManageEventUCC {

    
    public void addEvent(EventBean event) throws SendEmailException {

        EventDao eventDao = new EventDao() ;

        eventDao.addEvent(event.getDate(), event.getName(), event.getStart(), event.getEnd(), event.getDescription(), event.getType());
        SetReminderEmailBoundary.sendToServer(event, false) ;

    }

    public void update(EventBean event, EventBean oldEvent) throws SendEmailException{
        EventDao dao  = new EventDao();
        dao.updateEvento(event.getDate(), event.getName(), event.getStart(), event.getEnd(), event.getDescription(), oldEvent.getName(), event.getType());

        SetReminderEmailBoundary.sendToServer(event, true) ;
    }
}
