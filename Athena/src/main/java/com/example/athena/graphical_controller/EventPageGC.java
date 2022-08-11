package com.example.athena.graphical_controller;

import com.example.athena.view.EventsView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;


public class EventPageGC implements PostInitialize{
    @FXML
    private Label label1 ;
    @FXML
    private SubScene results ;

    private EventsView eventsView ;
    private LocalDate dayToLoad ;


    @Override
    public void postInitialize(ArrayList<Object> params) {
        this.eventsView = new EventsView(results.getWidth(), results.getHeight(), this) ;
        label1.setText(String.valueOf(params.get(0))) ;
        this.dayToLoad = (LocalDate)params.get(0) ;
        this.results.setRoot(eventsView.getRoot(this.dayToLoad));
    }

    public void refresh() {
        this.results.setRoot(eventsView.getRoot(dayToLoad));
    }


    public void close(javafx.event.ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
