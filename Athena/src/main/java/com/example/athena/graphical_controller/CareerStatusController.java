package com.example.athena.graphical_controller;

import com.example.athena.entities.ExamDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class CareerStatusController implements Initializable {
    @FXML
    private PieChart examsPieChart;
    @FXML
    private PieChart examsPieChartcfu ;

    @FXML
    private Label totalExams ;
    @FXML
    private Label takenExams ;
    @FXML
    private Label gainedCfus ;
    @FXML
    private Label totalCfus ;

    @FXML
    public void initialize(URL url , ResourceBundle rb) {

        ExamDao exam = new ExamDao() ;
        ObservableList<PieChart.Data> examsPieChartData  = exam.loadData() ;
        ObservableList<PieChart.Data> examsPieChartDatacfus  = exam.loadData2() ;

        int esamiDAti = (int) exam.getTotalExams();
        int esamiTotali = Integer.parseInt(totalExams.getText()) ;
        int cfuDati = (int) exam.getTotalCfus() ;
        int cfuTotali = Integer.parseInt(totalCfus.getText()) ;

        examsPieChartData.add(new PieChart.Data("Esami mancanti" , esamiTotali-esamiDAti)) ;
        examsPieChartDatacfus.add(new PieChart.Data("cfu mancanti " , cfuTotali - cfuDati)) ;

        takenExams.setText(String.valueOf(esamiDAti));
        gainedCfus.setText(String.valueOf(cfuDati));

        examsPieChart.setData(examsPieChartData);
        examsPieChartcfu.setData(examsPieChartDatacfus) ;
        examsPieChart.setStartAngle(90);

    }
    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
