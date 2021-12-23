package com.example.athena;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class addExamUseCaseController {





    public void addExam (examEntityBean esameBean , ActionEvent event , boolean update ,String oldName) throws IOException {


        if ((esameBean.getExamName().equals("")) || (esameBean.getVotoEsame() < 18 || esameBean.getVotoEsame() > 30) || (esameBean.getCfuEsame() < 0 || esameBean.getCfuEsame() > 15) ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, " Exam fields are not valid, check if you fill them correctly  ", ButtonType.CLOSE);
            alert.showAndWait();

        } else {
            if (!(update)) {


                examDAO esame = new examDAO();
                esame.addExam(esameBean);


                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();


            } else {
                examDAO esame = new examDAO();
                esame.updateExam(esameBean , oldName);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();

            }
        }



        }




    }
















