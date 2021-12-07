package com.example.athena;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;


public class addExamGraphicalController {
    @FXML
    private TextField nomeEsame;
    @FXML
    private TextField votoEsame;
    @FXML
    private TextField cfuEsame;
    @FXML
    private DatePicker dataEsame;


    public void confermaEsame(ActionEvent event) {
         String nome = nomeEsame.getText();
         LocalDate data = dataEsame.getValue();
         String Voto = votoEsame.getText();
         String CFU = cfuEsame.getText();

         examEntityBean examBean = new examEntityBean();

             examBean.setExamName(nome);
             examBean.setVotoEsame(Voto);
             examBean.setCfuEsame(CFU);
             examBean.setDate(data);

         addExamUseCaseController useCaseController = new addExamUseCaseController() ;
         useCaseController.addExam(examBean , event );





    }
    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
