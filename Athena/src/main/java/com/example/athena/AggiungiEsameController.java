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


public class AggiungiEsameController {
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

    private entityExam esameDaAggiungere;

    public void ConfermaEsame(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         nome = Nome_Esame.getText();
         data = Data_Esame.getValue();
        String Voto = Voto_Esame.getText();
        String Cfu = CFU_Esame.getText();
        try {
              voto = Integer.parseInt(Voto);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, " Metti un voto valido " , ButtonType.CLOSE);
            alert.showAndWait();
            return ;

        }

        try {
            cfu = Integer.parseInt(Cfu);
        } catch (NumberFormatException e ){
            Alert alert = new Alert(Alert.AlertType.ERROR, " Metti dei cfu  validi " , ButtonType.CLOSE);
            alert.showAndWait();
            return ;

        }

        if ( (nome.equals("")) || (voto<18 || voto>30) || (cfu<0 || cfu >15) ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, " Dati inseriti non validi " , ButtonType.CLOSE);
            alert.showAndWait();
        }
        else {
            esameDaAggiungere = new entityExam(nome, voto ,cfu ,data);
            System.out.println("Esame Aggiunto :)");
            stage.close();

        }

    }
    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
