package com.example.athena.use_case_controllers;


import com.example.athena.boundaries.SetReminderEmailBoundary;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.graphical_controller.EventBean;
import com.example.athena.entities.EventDao;

import java.time.LocalDateTime;

public class AddEventUCC {


    public void addEvent(EventBean evento , boolean update , String oldname) throws SendEmailException {

        EventDao eventDao = new EventDao() ;
        if (!update) {
            eventDao.addEvent(evento.getDate(), evento.getName(), evento.getStart(), evento.getEnd(), evento.getDescription(), evento.getType());
        }
        else {
            eventDao.updateEvento(evento.getDate() , evento.getName() , evento.getStart(), evento.getEnd(), evento.getDescription() , oldname, evento.getType());
        }

        if(evento.getIsThereAReminder())
        {
            if(evento.getDateOfReminder().isBefore(LocalDateTime.now())) throw new SendEmailException("The reminder would have to be sent before now") ;
            SetReminderEmailBoundary.sendToServer(evento) ;
        }
    }
}
