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

    @Override
    public void postInitialize(ArrayList<Object> params) {
        EventsView eventsView = new EventsView(results.getWidth(), results.getHeight());
        label1.setText(String.valueOf(params.get(0))) ;
        LocalDate dayToLoad = (LocalDate) params.get(0);
        this.results.setRoot(eventsView.getRoot(dayToLoad));
    }

    public void close(javafx.event.ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
