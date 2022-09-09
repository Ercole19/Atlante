package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.entities.ExamsOrCfusEnum;
import com.example.athena.exceptions.CareerStatusException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.beans.normal.SetMaxCfusOrExamsBean;
import com.example.athena.use_case_controllers.SetMaxCfusOrExamsUCC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;



import java.util.ArrayList;

public class SetMaxCfusOrExamsGC implements PostInitialize {


    @FXML
    private Text textSetting;


    @FXML
    private TextField textFieldMax;
    private ExamsOrCfusEnum examsOrCfus;


    @Override
    public void postInitialize(ArrayList<Object> params) {

        examsOrCfus = (ExamsOrCfusEnum) params.get(0);

        if(examsOrCfus == ExamsOrCfusEnum.SET_MAX_CFUS) {
            textSetting.setText("Set max cfus");
        } else {
            textSetting.setText("Set max exams");
        }
    }

    public void onConfirmBtnClick(ActionEvent event) {
        SetMaxCfusOrExamsUCC controllerCfusExams = new SetMaxCfusOrExamsUCC();
        try {
            SetMaxCfusOrExamsBean infos = new SetMaxCfusOrExamsBean();
            infos.setNewMax(textFieldMax.getText());
            infos.setType(this.examsOrCfus);

            controllerCfusExams.setInfos(infos);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

        } catch (CareerStatusException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800, 600);
            alert.showAndWait();
        }
    }

    public void onBackBtnClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
