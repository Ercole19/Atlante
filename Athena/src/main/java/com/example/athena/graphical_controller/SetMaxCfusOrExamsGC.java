package com.example.athena.graphical_controller;

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
    private boolean cfusOrExams;
    @FXML
    private TextField textFieldMax;


    @Override
    public void postInitialize(ArrayList<Object> params) {

        cfusOrExams = (boolean) params.get(0);
        controller = (CareerStatusController) params.get(1);
        if (cfusOrExams) {
            textSetting.setText("Set max cfus");
        } else {
            textSetting.setText("Set max exams");
        }
    }

    public void onConfirmBtnClick(ActionEvent event) {
        SetMaxCfusOrExamsUCC controllerCfusExams = new SetMaxCfusOrExamsUCC();
        try {
            int max = Integer.parseInt(textFieldMax.getText());
            controllerCfusExams.setInfos(max, cfusOrExams);

            if(cfusOrExams ){
               controller.setTotalCfus(max);
            }
            else
            {
                controller.setTotalExams(max);
            }
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
