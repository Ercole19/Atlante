package com.example.athena.GraphicalController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.example.athena.View.eventDao ;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


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
            System.out.println(evento.getEnd().toString());
            i = i + 4;
        }



        return result ;
    }


}
