package com.example.athena.UseCaseControllers;


import com.example.athena.GraphicalController.eventBean;
import com.example.athena.Entities.eventDao;

public class addEventUCC {


    public void addEvent(eventBean evento) {


        eventDao eventDao = new eventDao() ;
        eventDao.addEvent(evento.getDate() , evento.getName() , evento.getStart() ,evento.getEnd() ,evento.getDescription());




    }



}
