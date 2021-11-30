package com.example.athena;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class careerStatusController implements Initializable {
    @FXML
    private PieChart examsPieChart;
    @FXML
    public void initialize(URL url , ResourceBundle rb) {
        ObservableList<PieChart.Data> examsPieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Esami dati" , 45) ,
                new PieChart.Data("Esami mancanti" , 55)
        );
        examsPieChart.setData(examsPieChartData);
        examsPieChart.setStartAngle(90);

    }
    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
