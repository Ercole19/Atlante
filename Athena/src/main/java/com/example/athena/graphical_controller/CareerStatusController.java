package com.example.athena.graphical_controller;

import com.example.athena.use_case_controllers.CareerStatusUCC;
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

        CareerStatusUCC controller = new CareerStatusUCC() ;
        ObservableList<PieChart.Data> examsPieChartData  = controller.retrieveExamPieChart() ;
        ObservableList<PieChart.Data> examsPieChartDatacfus  = controller.retrieveExamPieChartCfus() ;

        int esamiDAti = controller.retrieveTotalExams();
        int esamiTotali = Integer.parseInt(totalExams.getText()) ;
        int cfuDati = controller.retrieveTotalCfus() ;
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
