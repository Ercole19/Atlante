package com.example.athena.graphical_controller;

import com.example.athena.view.SearchResultFormatterView;
import com.example.athena.view.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.view.scene_decorators.SearchResultFormatterScrollBar;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import com.example.athena.entities.EventDao;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EventPageUcc implements PostInitialize{
    @FXML
    private Label label1 ;
    @FXML
    private SubScene results ;

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
            evento.setDate(LocalDate.parse(eventinfos[i+4]));
            result.add(evento);
            i = i + 5;
        }



        return result ;
    }


    @Override
    public void postInitialize(ArrayList<Object> params)
    {
        label1.setText((String) params.get(0)) ;

        List<EventBean> entries = (List<EventBean>)params.get(1) ;

        SearchResultFormatterComponent resultView = new SearchResultFormatterView() ;

        if(results.getHeight() < entries.size()*100.0)
        {
            resultView = new SearchResultFormatterScrollBar(resultView) ;
        }

        AnchorPane subSceneElems = resultView.buildEventSearchResultsScene(results.getWidth(), results.getHeight(), (ArrayList<EventBean>) entries) ;
        results.setRoot(subSceneElems) ;
    }
}
