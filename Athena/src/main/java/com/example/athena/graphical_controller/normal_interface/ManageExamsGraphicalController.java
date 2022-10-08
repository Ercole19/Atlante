package com.example.athena.graphical_controller.normal_interface;


import com.example.athena.beans.ExamBean;
import com.example.athena.beans.OutputExamBean;
import com.example.athena.exceptions.ExamException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.beans.normal.NormalExamBean;
import com.example.athena.use_case_controllers.ManageExamsUCC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;


public class ManageExamsGraphicalController implements PostInitialize {

    @FXML
    private TextField examName;
    @FXML
    private TextField examGrade;
    @FXML
    private TextField examCFU;
    @FXML
    private DatePicker examDate;
    @FXML
    private Button confirm;

    private  String name ;
    private ExamBean oldExam;

    public void onConfirmButtonClick(ActionEvent event) {

        NormalExamBean examBean = new NormalExamBean();
        try {
            int grade = Integer.parseInt(examGrade.getText());
            int cfu = Integer.parseInt(examCFU.getText());
            name = examName.getText();

            if (!((grade < 18 || grade > 30) || (cfu < 2 || cfu > 18))){
                examBean.setExamName(name);
                examBean.setExamGrade(examGrade.getText());
                examBean.setExamCfu(examCFU.getText());
                examBean.setExamDate(examDate.getValue());

                ManageExamsUCC useCaseController = new ManageExamsUCC();
                useCaseController.addExam(examBean);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
            else{
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Inserted data are not valid!", 800, 600);
                alert.showAndWait();
            }
        } catch (ExamException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        } catch (NumberFormatException e)
        {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Grades or cfus are not numbers", 800, 600) ;
            alert.showAndWait() ;
        }
    }


    public void updateExam(ActionEvent event) {

        NormalExamBean newExam = new NormalExamBean();
        try {
            int grade = Integer.parseInt(examGrade.getText());
            int cfu = Integer.parseInt(examCFU.getText());
            name = examName.getText();

            if(!((grade < 18 || grade > 30) || (cfu < 2 || cfu > 18))){
                newExam.setExamName(name);
                newExam.setExamGrade(examGrade.getText());
                newExam.setExamCfu(examCFU.getText());
                newExam.setExamDate(examDate.getValue());
            }

            ManageExamsUCC controller = new ManageExamsUCC();
            controller.updateExamFromDB(newExam, oldExam);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (ExamException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
        catch (NumberFormatException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Grades or cfus are not numbers", 800, 600) ;
            alert.showAndWait() ;
        }
    }


    public void onBackButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void postInitialize(ArrayList<Object> params) {
        oldExam = (NormalExamBean) params.get(0);

        examName.setText(oldExam.getExamName());
        examGrade.setText(String.valueOf(oldExam.getExamGrade()));
        examCFU.setText(String.valueOf(oldExam.getExamCfu()));
        examDate.setValue(LocalDate.parse(oldExam.getExamDate()));

        confirm.setText("Update");
        confirm.setOnAction(this::updateExam) ;
    }
}


