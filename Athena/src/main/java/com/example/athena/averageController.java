package com.example.athena;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;


public class averageController implements Initializable {
    @FXML
    private LineChart<String , Number> averageGraph;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data<String,Number>("12/01/2020", 28));
        series.getData().add(new XYChart.Data<String , Number>("17/02/2020", 26));



        averageGraph.getData().addAll(series);





    }
    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}