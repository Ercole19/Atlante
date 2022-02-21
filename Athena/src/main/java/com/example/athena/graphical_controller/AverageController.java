package com.example.athena.graphical_controller;

import com.example.athena.use_case_controllers.AverageUCC;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class AverageController implements Initializable {
    @FXML
    private LineChart<String , Number> averageGraph;
    @FXML
    private Label labelAverageArit ;
    @FXML
    private Label labelAverageWei ;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AverageUCC controller = new AverageUCC() ;

        ObservableList<XYChart.Data<String, Number>> sortedExams = controller.retrieveExams();
        ObservableList<XYChart.Data<String, Number>> weightedSortedExams = controller.retrieveExamsWeighted() ;

        XYChart.Series<String, Number> series = new XYChart.Series<>("Artithmetic average ", sortedExams);
        XYChart.Series<String, Number> series2 = new XYChart.Series<>("Weighted average ", weightedSortedExams);

        averageGraph.getData().addAll(series, series2) ;

        Number average = controller.retrieveAverage() ;
        Number weightedAverage = controller.retrieveWeightedAverage() ;

        labelAverageArit.setText(String.valueOf(String.format("%.2f", average)));
        labelAverageWei.setText(String.valueOf(String.format("%.2f" , weightedAverage)));
    }

    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}