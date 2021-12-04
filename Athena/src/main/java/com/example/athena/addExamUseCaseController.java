package com.example.athena;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.time.LocalDate;

public class addExamUseCaseController {

    private entityExam esameDaAggiungere;
    private String examName ;
    private int examVote;
    private int examCFUs;
    private LocalDate examDate ;

    public addExamUseCaseController(examEntityBean esameBean)  {

        if ((esameBean.getExamName().equals("")) || (esameBean.getVotoEsame()<18 || esameBean.getVotoEsame()>30) || (esameBean.getCfuEsame()<0 || esameBean.getCfuEsame()>15)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, " Fill correctly all the fields " , ButtonType.CLOSE);
            alert.showAndWait();
        }
        else {
            esameDaAggiungere = new entityExam(esameBean.getExamName() , esameBean.getVotoEsame() , esameBean.getCfuEsame() , esameBean.getDate()) ;
            System.out.println("Exam added :) ");

        }





    }



}
