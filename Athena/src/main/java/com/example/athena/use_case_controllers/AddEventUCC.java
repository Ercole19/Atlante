package com.example.athena.use_case_controllers;


import com.example.athena.boundaries.SetReminderEmailBoundary;
import com.example.athena.entities.EventDao;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.graphical_controller.EventBean;

import java.time.LocalDateTime;

public class AddEventUCC {


    public void addEvent(EventBean evento) throws SendEmailException {

        EventDao eventDao = new EventDao() ;

        eventDao.addEvent(evento.getDate(), evento.getName(), evento.getStart(), evento.getEnd(), evento.getDescription(), evento.getType());


        if(evento.getIsThereAReminder())
        {
            if(evento.getDateOfReminder().isBefore(LocalDateTime.now())) throw new SendEmailException("The reminder would have to be sent before now") ;
            SetReminderEmailBoundary.sendToServer(evento) ;
        }
    }

    public void update(EventBean bean, String oldname) {
        EventDao dao  = new EventDao();
        dao.updateEvento(bean.getDate(), bean.getName(), bean.getStart(), bean.getEnd(), bean.getDescription(), oldname, bean.getType());

    }
}
