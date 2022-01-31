package com.example.athena.GraphicalController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.example.athena.Entities.eventDao;

import java.time.LocalDate;
import java.util.ArrayList;


public class eventPageUCC  {
    @FXML
    private Label label1 ;

    eventDao course = new eventDao() ;

    public ArrayList<eventBean> formatSearchResultsByDate(LocalDate data) {
        int i = 0 ;
        String[] eventinfos = course.findeventinfo(data.toString()) ;
        ArrayList<eventBean> result = new ArrayList<>();
        while (eventinfos[i] != null) {

            eventBean evento = new eventBean() ;
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
