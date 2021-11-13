package com.example.atena;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller_Homepage_Carriera {
    protected static Stage stage;
    private Scene scene;
    private TextField NomeEsame;

    public void Inizializza_Aggiungi_Esame () throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Aggiungi_Esame_view.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setTitle("Aggiungi esame");
        stage.setScene(scene);
        stage.showAndWait();

    }
    public void Inizializza_Mostra_Media () throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Mostra_Media_View.fxml"));
        stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("La tua media");
        stage.showAndWait();

    }

    public void Conferma_Esame(ActionEvent actionEvent) throws IOException {
        NomeEsame = (TextField) stage.getScene().lookup("#Nome_Esame");
        if (NomeEsame.getText().equals("")) {
            System.out.println("Aggiungi un nome prima!");
        }
        else {
            System.out.println("Esame Aggiunto!");
            stage.close();
        }
    }

    public void Indietro(){
        stage.close();
    }




}