package com.example.athena.graphical_controller;

import com.example.athena.view.EventsView;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.ArrayList;


public class EventPageGC implements PostInitialize{
    @FXML
    private Label label1 ;
    @FXML
    private SubScene results ;
    private EventsView eventsView = new EventsView(results.getWidth(), results.getHeight());


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
