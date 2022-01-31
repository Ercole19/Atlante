package com.example.athena.use_case_controllers;


import com.example.athena.graphical_controller.eventBean;
import com.example.athena.entities.EventDao;

public class addEventUCC {


    public void addEvent(eventBean evento) {


        EventDao eventDao = new EventDao() ;
        eventDao.addEvent(evento.getDate() , evento.getName() , evento.getStart() ,evento.getEnd() ,evento.getDescription());




    }



}
