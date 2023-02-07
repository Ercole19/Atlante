package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.SetMaxCfusOrExamsBean;
import com.example.athena.entities.ExamsOrCfusEnum;
import com.example.athena.entities.PersonalTakenExams;
import com.example.athena.exceptions.*;
import com.example.athena.use_case_controllers.SetMaxCfusOrExamsUCC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
        SetMaxCfusOrExamsBean infos = new SetMaxCfusOrExamsBean();
        try {
            infos.setNewMax(textFieldMax.getText());
            if ((this.examsOrCfus.toString().equals(("SET_MAX_CFUS")) && (Integer.parseInt(textFieldMax.getText()) < PersonalTakenExams.getInstance().getGainedCfusNumber()))) {
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "New max cfu can not be lower than gained cfus", 800, 600);
                alert.showAndWait();
            } else if (this.examsOrCfus.toString().equals(("SET_MAX_EXAMS")) && (Integer.parseInt(textFieldMax.getText()) < PersonalTakenExams.getInstance().getTakenExamsNumber())) {
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "New max exams can not be lower than taken exams", 800, 600);
                alert.showAndWait();
            }
            else {
                infos.setType(this.examsOrCfus);
                controllerCfusExams.setInfos(infos);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }

        } catch (CareerStatusException | ExamException | StudentInfoException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800, 600);
            alert.showAndWait();
        }
    }

    public void onBackBtnClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
