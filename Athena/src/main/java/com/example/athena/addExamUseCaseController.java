package com.example.athena;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class addExamUseCaseController {



    public void addExam (examEntityBean esameBean) {



            if ((esameBean.getExamName().equals("")) || (esameBean.getVotoEsame() < 18 || esameBean.getVotoEsame() > 30) || (esameBean.getCfuEsame() < 0 || esameBean.getCfuEsame() > 15)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Exam fields are not valid, check if you fill them correctly  ", ButtonType.CLOSE);
                alert.showAndWait();

            } else {
                entityExam  esameDaAggiungere = new entityExam(esameBean.getExamName(), esameBean.getVotoEsame(), esameBean.getCfuEsame(), esameBean.getDate());
                System.out.println(esameBean.getVotoEsame());



            }
        }

}











