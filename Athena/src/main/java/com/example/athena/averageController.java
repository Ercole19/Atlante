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
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;


public class averageController implements Initializable {
    @FXML
    private LineChart<String , Number> averageGraph;
    @FXML
    private Label labelAverageArit ;

    private examDAO exam ;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exam = new examDAO() ;

        ObservableList<XYChart.Data<String, Number>> data = exam.getSortedExams() ;

        XYChart.Series<String, Number> series = new XYChart.Series<>("Average ", data);







        averageGraph.getData().addAll(series);
















    }
    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}