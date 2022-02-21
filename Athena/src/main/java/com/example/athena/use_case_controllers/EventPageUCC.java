package com.example.athena.use_case_controllers;

import com.example.athena.entities.EventDao;
import com.example.athena.graphical_controller.EventBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventPageUCC {
    public List<EventBean> formatSearchResultsByDate(LocalDate data) {
        int i = 0 ;
        EventDao course = new EventDao();
        String[] eventinfos = course.findeventinfo(data.toString()) ;
        ArrayList<EventBean> result = new ArrayList<>();
        while (eventinfos[i] != null) {

            EventBean evento = new EventBean() ;
            evento.setName(eventinfos[i]);
            evento.setStartbyString(eventinfos[i + 1]);
            evento.setEndbyString(eventinfos[i + 2]);
            evento.setDescription(eventinfos[i + 3]);
            evento.setDate(LocalDate.parse(eventinfos[i+4]));
            evento.setType(eventinfos[i+5]) ;
            result.add(evento);
            i = i + 6;
        }



        return result ;
    }
}
