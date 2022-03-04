package com.example.athena.graphical_controller;

import com.example.athena.entities.ExamsOrCfusEnum;
import com.example.athena.entities.UserDao;
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
    private CareerStatusController controller;

    @FXML
    private TextField textFieldMax;
    private ExamsOrCfusEnum examsOrCfus;


    @Override
    public void postInitialize(ArrayList<Object> params) {

        examsOrCfus = (ExamsOrCfusEnum) params.get(0);
        controller = (CareerStatusController) params.get(1);
        switch(examsOrCfus)
        {
            case SET_MAX_CFUS:
                textSetting.setText("Set  max cfus");
                break;

            case SET_MAX_EXAMS:
                textSetting.setText("Set max exams");
                break;
        }
    }

    public void onConfirmBtnClick(ActionEvent event) {
        SetMaxCfusOrExamsUCC controllerCfusExams = new SetMaxCfusOrExamsUCC();
        try {
            int max = Integer.parseInt(textFieldMax.getText());
            SetMaxCfusOrExamsBean infos = new SetMaxCfusOrExamsBean() ;
            infos.setNewMax(max) ;
            infos.setType(this.examsOrCfus) ;

            controllerCfusExams.setInfos(infos);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
        catch (NumberFormatException exc){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Insert a number!", ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

    public void onBackBtnClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
