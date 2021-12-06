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
        series.setName("Average");

        series.getData().add(new XYChart.Data<String,Number>("12/01/2020", 28)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/02/2020", 26)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/03/2020", 27)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/04/2020", 29)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/05/2020", 24)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/06/2020", 25)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/07/2020", 22)) ;
        series.getData().add(new XYChart.Data<String,Number>("12/01/2021", 28)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/02/2021", 26)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/03/2021", 27)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/04/2021", 29)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/05/2021", 24)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/06/2021", 25)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/07/2021", 22)) ;
        series.getData().add(new XYChart.Data<String,Number>("12/01/2022", 28)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/02/2022", 26)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/03/2022", 27)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/04/2022", 29)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/05/2022", 24)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/06/2022", 25)) ;
        series.getData().add(new XYChart.Data<String , Number>("17/07/2022", 22)) ;




        averageGraph.getData().addAll(series);





    }
    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}