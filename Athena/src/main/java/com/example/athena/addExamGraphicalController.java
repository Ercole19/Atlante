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
import java.util.Locale;


public class addExamGraphicalController {
    @FXML
    private TextField Nome_Esame;
    @FXML
    private TextField Voto_Esame;
    @FXML
    private TextField CFU_Esame;
    @FXML
    private DatePicker Data_Esame;

    private int voto;

    private int cfu;

    private String nome;
    private LocalDate data;
    private String Voto ;
    private String CFU ;

    private  examEntityBean examBean;
    private addExamUseCaseController controllerUseCase ;

    public void ConfermaEsame(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         nome = Nome_Esame.getText();
         data = Data_Esame.getValue();
         Voto = Voto_Esame.getText();
         CFU = CFU_Esame.getText();

         examBean = new examEntityBean(nome , Voto , CFU , data);
         controllerUseCase = new addExamUseCaseController(examBean) ;
         stage.close();





    }
    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
