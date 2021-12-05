package com.example.athena;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         String nome = nomeEsame.getText();
         LocalDate data = dataEsame.getValue();
         String Voto = votoEsame.getText();
         String CFU = cfuEsame.getText();

         examEntityBean examBean = new examEntityBean(nome , Voto , CFU , data);
         addExamUseCaseController useCaseController = new addExamUseCaseController() ;
         useCaseController.addExam(examBean);





    }
    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
