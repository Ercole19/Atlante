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





public class AggiungiEsameController {


    public void ConfermaEsame(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        TextField nomeEsame = (TextField) stage.getScene().lookup("#Nome_Esame");
        if (!(nomeEsame.getText().equals(""))) {
            stage.close();
        }

    }
    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
