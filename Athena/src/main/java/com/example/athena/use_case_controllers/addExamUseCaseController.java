package com.example.athena.use_case_controllers;

import com.example.athena.graphical_controller.examEntityBean;
import com.example.athena.entities.ExamDao;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class addExamUseCaseController {





    public void addExam (examEntityBean esameBean , ActionEvent event , boolean update , String oldName) throws IOException {


        if ((esameBean.getExamName().equals("")) || (esameBean.getVotoEsame() < 18 || esameBean.getVotoEsame() > 30) || (esameBean.getCfuEsame() < 0 || esameBean.getCfuEsame() > 15) ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, " Exam fields are not valid, check if you fill them correctly  ", ButtonType.CLOSE);
            alert.showAndWait();

        } else {
            if (!(update)) {


                ExamDao esame = new ExamDao();
                esame.addExam(esameBean);


                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();


            } else {
                ExamDao esame = new ExamDao();
                esame.updateExam(esameBean , oldName);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();

            }
        }



        }




    }
















