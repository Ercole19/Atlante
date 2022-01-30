package com.example.athena.GraphicalController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;

public class sellController {

    private final SceneSwitcher switcher = new SceneSwitcher();

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "bookshop-choose-view.fxml");
    }

    public void onSellBtnClick() throws IOException{
        FXMLLoader loader = new FXMLLoader(switcher.generateUrl( "sellBookModule.fxml")) ;
        Stage stage = new Stage() ;
        stage.initModality(Modality.APPLICATION_MODAL) ;
        stage.setResizable(false) ;
        Scene scene = new Scene(loader.load()) ;
        stage.setTitle("Sell a book") ;
        stage.setScene(scene) ;
        stage.showAndWait() ;
    }
}
