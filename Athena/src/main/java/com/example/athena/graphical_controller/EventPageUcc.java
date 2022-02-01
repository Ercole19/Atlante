package com.example.athena.graphical_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.example.athena.entities.EventDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EventPageUcc {
    @FXML
    private Label label1 ;

    EventDao course = new EventDao() ;

    public List<EventBean> formatSearchResultsByDate(LocalDate data) {
        int i = 0 ;
        String[] eventinfos = course.findeventinfo(data.toString()) ;
        ArrayList<EventBean> result = new ArrayList<>();
        while (eventinfos[i] != null) {

            EventBean evento = new EventBean() ;
            evento.setName(eventinfos[i]);
            evento.setStartbyString(eventinfos[i + 1]);
            evento.setEndbyString(eventinfos[i + 2]);
            evento.setDescription(eventinfos[i + 3]);
            result.add(evento);
            i = i + 4;
        }



        return result ;
    }


}
