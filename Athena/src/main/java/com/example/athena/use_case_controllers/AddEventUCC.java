package com.example.athena.use_case_controllers;


import com.example.athena.graphical_controller.EventBean;
import com.example.athena.entities.EventDao;

public class AddEventUCC {


    public void addEvent(EventBean evento) {


        EventDao eventDao = new EventDao() ;
        eventDao.addEvent(evento.getDate() , evento.getName() , evento.getStart() ,evento.getEnd() ,evento.getDescription());




    }



}
