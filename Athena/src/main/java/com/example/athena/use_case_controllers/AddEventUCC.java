package com.example.athena.use_case_controllers;


import com.example.athena.graphical_controller.EventBean;
import com.example.athena.entities.EventDao;

public class AddEventUCC {


    public void addEvent(EventBean evento , boolean update , String oldname) {


        EventDao eventDao = new EventDao() ;
        if (!update) {
            eventDao.addEvent(evento.getDate(), evento.getName(), evento.getStart(), evento.getEnd(), evento.getDescription(), evento.getType());
        }
        else {
            eventDao.updateEvento(evento.getDate() , evento.getName() , evento.getStart(), evento.getEnd(), evento.getDescription() , oldname, evento.getType());
        }




    }



}
