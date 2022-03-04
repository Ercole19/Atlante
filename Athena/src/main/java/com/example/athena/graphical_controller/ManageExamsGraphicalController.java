package com.example.athena.graphical_controller;


import com.example.athena.use_case_controllers.AddExamUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class ManageExamsGraphicalController implements PostInitialize {

    @FXML
    private TextField nomeEsame;
    @FXML
    private TextField votoEsame;
    @FXML
    private TextField examCFU;
    @FXML
    private DatePicker examDate;
    @FXML
    private Button confirm;
    private String oldName ;


    public void onConfirmButtonClick(ActionEvent event) {

        ExamEntityBean examBean = new ExamEntityBean();
        int grade = Integer.parseInt(examGrade.getText());
        int cfu = Integer.parseInt(examCFU.getText());
        name = examName.getText();
        date = examDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        try {
            if (!((grade < 18 || grade > 30) || (cfu < 2 || cfu > 18))){
                examBean.setExamName(name);
                examBean.setVotoEsame(grade);
                examBean.setCfuEsame(cfu);
                examBean.setDate(date);

                ManageExamsUCC useCaseController = new ManageExamsUCC();
                useCaseController.addExam(examBean);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
            else{
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Inserted data are not valid!", 800, 600);
                alert.showAndWait();
            }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    public void updateExam(ActionEvent event) {

        ExamEntityBean bean = new ExamEntityBean() ;

        try {
            if(!((grade < 18 || grade > 30) || (cfu < 2 || cfu > 18))){
                newExam.setExamName(name);
                newExam.setVotoEsame(grade);
                newExam.setCfuEsame(cfu);
                newExam.setDate(date);
            }

            ManageExamsUCC controller = new ManageExamsUCC();
            controller.updateExamFromDB(newExam, oldExam);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (ExamException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }


    public void indietro(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void postInitialize(ArrayList<Object> params) {


        ExamEntityBean exam = (ExamEntityBean) params.get(0);
        oldName = exam.getExamName() ;


        examName.setText(oldExam.getExamName());
        examGrade.setText(String.valueOf(oldExam.getVotoEsame()));
        examCFU.setText(String.valueOf(oldExam.getCfuEsame()));
        examDate.setValue(LocalDate.parse(oldExam.getDate()));


        confirm.setText("Update");
        confirm.setOnAction(this::updateExam) ;
    }


}


