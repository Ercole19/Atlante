package com.example.athena;


import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

import static javafx.fxml.FXMLLoader.load;





public class AggiungiEsameController {
    private entityExam esameDaAggiungere;

    public void ConfermaEsame(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        TextField nomeEsame = (TextField) stage.getScene().lookup("#Nome_Esame");
        TextField votoEsame = (TextField) stage.getScene().lookup("#votoEsame");
        TextField CFUEsame = (TextField) stage.getScene().lookup(("#CFU_Esame"));
        DatePicker dataEsame = (DatePicker) stage.getScene().lookup("#Data_Esame");
        String nome = nomeEsame.getText();
        LocalDate data = dataEsame.getValue();
        if ( (nome.equals("")) ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, " Aggiungi un nome all'esame " , ButtonType.CLOSE);
            alert.showAndWait();
        }
        else {
            int voto = Integer.parseInt(votoEsame.getText());
            int cfu = Integer.parseInt(CFUEsame.getText());
            esameDaAggiungere = new entityExam(nome,voto ,cfu ,data);
            System.out.println("Esame aggiunto:\n"+ esameDaAggiungere.nome + "/" + esameDaAggiungere.voto + "/" + esameDaAggiungere.CFU + "/" + esameDaAggiungere.data);
            stage.close();

        }

    }
    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
