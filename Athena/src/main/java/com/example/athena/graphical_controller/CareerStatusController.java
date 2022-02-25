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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CareerStatusController implements Initializable {
    @FXML
    private PieChart examsPieChart;
    @FXML
    private PieChart examsPieChartcfu ;

    private int esamiTotali;
    private int cfuTotali;



    @FXML
    private Label totalExams ;
    @FXML
    private Label takenExams ;
    @FXML
    private Label gainedCfus ;
    @FXML
    private Label totalCfus ;

    private final SceneSwitcher switcher =  new SceneSwitcher();
    private Stage stage;

    @FXML
    public void initialize(URL url , ResourceBundle rb) {

        CareerStatusUCC controller = new CareerStatusUCC() ;
        ObservableList<PieChart.Data> examsPieChartData  = controller.retrieveExamPieChart() ;
        ObservableList<PieChart.Data> examsPieChartDatacfus  = controller.retrieveExamPieChartCfus() ;

        int esamiDAti = controller.retrieveTotalTakenExams();
        esamiTotali = controller.retrieveTotalExams();
        cfuTotali = controller.retrieveTotalCfus();
        int cfuDati = controller.retrieveTotalTakenCfus() ;

        examsPieChartData.add(new PieChart.Data("Esami mancanti" , esamiTotali-esamiDAti)) ;
        examsPieChartDatacfus.add(new PieChart.Data("cfu mancanti " , cfuTotali - cfuDati)) ;

        takenExams.setText(String.valueOf(esamiDAti));
        gainedCfus.setText(String.valueOf(cfuDati));

        totalCfus.setText(String.valueOf(cfuTotali));
        totalExams.setText(String.valueOf(esamiTotali));

        examsPieChart.setData(examsPieChartData);
        examsPieChartcfu.setData(examsPieChartDatacfus) ;
        examsPieChart.setStartAngle(90);

    }
    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onSetCfuBtn()
    {
        SceneSwitcher switcher = new SceneSwitcher();
        ArrayList<Object> params = new ArrayList<>();

        params.add(true);
        params.add(this);//true if i want to set cfu, else exams. I will use this in a postInitialize
        try {
            switcher.popup("SetMAxCfuOrExams.fxml", "Set max cfus", params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSetExamsBtn() {
        ArrayList<Object> params = new ArrayList<>();

        params.add(false);
        params.add(this);
        switcher.popup("SetMAxCfuOrExams.fxml", "Set max cfus", params);

    }

    public void setTotalExams(int exmasTotal){
        totalExams.setText(String.valueOf(exmasTotal));
    }

    public void setTotalCfus(int cfusTotal){
        totalCfus.setText(String.valueOf(cfusTotal));
    }


}
