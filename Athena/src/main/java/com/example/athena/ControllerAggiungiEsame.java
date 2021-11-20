package com.example.athena;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;



public class ControllerAggiungiEsame {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initAggiungiEsame () throws IOException {
        root = FXMLLoader.load(getClass().getResource("Aggiungi_Esame_view.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setTitle("Aggiungi esame");
        stage.setScene(scene);
        stage.showAndWait();

    }
    public void initMostraMedia () throws IOException {
        root = FXMLLoader.load(getClass().getResource("Mostra_Media_View.fxml"));
        stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("La tua media");
        stage.showAndWait();

    }

    public void confermaEsame (ActionEvent event)  {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        TextField nomeEsame = (TextField) stage.getScene().lookup("#Nome_Esame");
        if (!(nomeEsame.getText().equals(""))) {
            stage.close();
        }


    }

    public void indietro(ActionEvent event){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }



}