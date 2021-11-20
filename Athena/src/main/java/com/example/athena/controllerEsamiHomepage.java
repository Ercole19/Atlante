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
import java.net.URL;
import java.util.Objects;
import static javafx.fxml.FXMLLoader.load;



public class controllerEsamiHomepage {
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

    public void onBackButtonClick(ActionEvent event) throws IOException {
        root = load(Objects.requireNonNull(getClass().getResource("MainPage.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

        public void indietro (ActionEvent event) {
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
        }


}