package com.example.athena.graphical_controller;

import com.example.athena.entities.ExamsOrCfusEnum;
import com.example.athena.exceptions.ExamException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.CareerStatusUCC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    private final SceneSwitcher switcher =  new SceneSwitcher();

    @FXML
    public void initialize(URL url , ResourceBundle rb) {

        try {

            CareerStatusUCC controller = new CareerStatusUCC();
            CareerInformationBean infos = controller.getAllInfos();

            int infosTakenExams = infos.getTakenExams();
            int infosTotalExams = infos.getTotalExams();
            int infosGainedCfus = infos.getGainedCfus();
            int infosTotalCfus = infos.getTotalCfus();

            ObservableList<PieChart.Data> examsData = FXCollections.observableArrayList();
            ObservableList<PieChart.Data> cfusData = FXCollections.observableArrayList();

            examsData.add(new PieChart.Data("Taken Exams", infosTakenExams ));
            cfusData.add(new PieChart.Data("Gained Cfus", infosGainedCfus));

            examsData.add(new PieChart.Data("Remaining exams", infosTotalExams - infosTakenExams));
            cfusData.add(new PieChart.Data("Remaining cfus", infosTotalCfus - infosGainedCfus));

            takenExams.setText(String.valueOf(infosTakenExams));
            gainedCfus.setText(String.valueOf(infosGainedCfus));

            totalCfus.setText(String.valueOf(infosTotalCfus));
            totalExams.setText(String.valueOf(infosTotalExams));

            examsPieChart.setData(examsData);
            examsPieChartcfu.setData(cfusData);
            examsPieChart.setStartAngle(90);
        }
        catch (ExamException exc) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, exc.getMessage(), 800, 600) ;
            alert.showAndWait() ;
        }

    }
    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onSetCfuBtn() {
        List<Object> params = new ArrayList<>();

        params.add(ExamsOrCfusEnum.SET_MAX_CFUS) ;
        switcher.popup("SetMAxCfuOrExams.fxml", "Set max cfus", params);

    }

    public void onSetExamsBtn() {
        List<Object> params = new ArrayList<>();

        params.add(ExamsOrCfusEnum.SET_MAX_EXAMS);
        switcher.popup("SetMAxCfuOrExams.fxml", "Set max cfus", params);


    }

    public void setTotalExams(int exmasTotal){
        totalExams.setText(String.valueOf(exmasTotal));
    }

    public void setTotalCfus(int cfusTotal){
        totalCfus.setText(String.valueOf(cfusTotal));
    }


}
