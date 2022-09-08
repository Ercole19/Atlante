package com.example.athena.graphical_controller.normal_interface;


import com.example.athena.exceptions.ExamException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.beans.normal.ExamAverageInformationBean;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.use_case_controllers.AverageUCC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
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
        try {
            AverageUCC controller = new AverageUCC();

            ObservableList<ExamAverageInformationBean> arithmeticAverageInformations = controller.getExamsArithmeticAverageInformation() ;
            ObservableList<ExamAverageInformationBean> weightedAverageInformations = controller.getExamsWeightedAverageInformation();

            ObservableList<XYChart.Data<String, Number>> arithmeticAverages = FXCollections.observableArrayList();
            ObservableList<XYChart.Data<String, Number>> weightedAverages = FXCollections.observableArrayList();

            for (ExamAverageInformationBean arithmeticInfo : arithmeticAverageInformations)
            {
                arithmeticAverages.add(new XYChart.Data<>(arithmeticInfo.getDate(), arithmeticInfo.getAverage()));
            }

            for (ExamAverageInformationBean weightedInfo : weightedAverageInformations)
            {
                weightedAverages.add(new XYChart.Data<>(weightedInfo.getDate(), weightedInfo.getAverage()));
            }

            XYChart.Series<String, Number> series = new XYChart.Series<>("Artithmetic average ", arithmeticAverages);
            XYChart.Series<String, Number> series2 = new XYChart.Series<>("Weighted average ", weightedAverages);

            averageGraph.getData().addAll(series, series2) ;

            double currentArithmeticAverage = arithmeticAverageInformations.get(arithmeticAverageInformations.size() - 1).getAverage();
            double currentWeightedAverage = weightedAverageInformations.get(weightedAverageInformations.size() - 1).getAverage();

            labelAverageArit.setText(String.valueOf(String.format("%.2f", currentArithmeticAverage)));
            labelAverageWei.setText(String.valueOf(String.format("%.2f" , currentWeightedAverage)));

        } catch (ExamException | UserInfoException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800, 600) ;
            alert.showAndWait() ;
        }
    }

    public void onBackBtnClick(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}