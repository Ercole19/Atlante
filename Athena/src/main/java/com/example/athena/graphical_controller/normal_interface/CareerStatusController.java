package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.engineering_classes.observer_pattern.AbstractObserver;
import com.example.athena.entities.ExamsOrCfusEnum;
import com.example.athena.entities.ExamsSubject;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.*;
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
    private PieChart examsPieChartcfu;
    @FXML
    private Label totalExams;
    @FXML
    private Label takenExams;
    @FXML
    private Label gainedCfus;
    @FXML
    private Label totalCfus;

    private final SceneSwitcher switcher = SceneSwitcher.getInstance();

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        try {

            takenExams.setText(String.valueOf(ExamsSubject.getInstance().getTakenExamsNumber()));
            gainedCfus.setText(String.valueOf(ExamsSubject.getInstance().getGainedCfusNumber()));
            totalCfus.setText(String.valueOf(LoggedStudent.getInstance().getCurrentStudent().getMaxCfu()));
            totalExams.setText(String.valueOf(LoggedStudent.getInstance().getCurrentStudent().getMaxExams()));
            setGraphs();

        } catch (ExamException exc) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, exc.getMessage(), 800, 600);
            alert.showAndWait();
        }

    }

    private void setGraphs() {
        ObservableList<PieChart.Data> examsData = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> cfusData = FXCollections.observableArrayList();
        examsData.add(new PieChart.Data("Taken Exams", Integer.parseInt(takenExams.getText())));
        cfusData.add(new PieChart.Data("Gained Cfus", Integer.parseInt(gainedCfus.getText())));

        examsData.add(new PieChart.Data("Remaining exams", Integer.parseInt(totalExams.getText()) - Integer.parseInt(takenExams.getText())));
        cfusData.add(new PieChart.Data("Remaining cfus", Integer.parseInt(totalCfus.getText()) - Integer.parseInt(gainedCfus.getText())));

        examsPieChart.setData(examsData);
        examsPieChartcfu.setData(cfusData);
        examsPieChart.setStartAngle(90);
    }

    public void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onSetCfuBtn() {
        List<Object> params = new ArrayList<>();
        params.add(ExamsOrCfusEnum.SET_MAX_CFUS);
        switcher.popup("SetMaxCfuOrExams.fxml", "Set max cfus", params);
        this.totalCfus.setText(String.valueOf(LoggedStudent.getInstance().getCurrentStudent().getMaxCfu()));
        setGraphs();


    }

    public void onSetExamsBtn() {
        List<Object> params = new ArrayList<>();
        params.add(ExamsOrCfusEnum.SET_MAX_EXAMS);
        switcher.popup("SetMaxCfuOrExams.fxml", "Set max cfus", params);
        this.totalExams.setText(String.valueOf(LoggedStudent.getInstance().getCurrentStudent().getMaxExams()));
        setGraphs();
    }

}
