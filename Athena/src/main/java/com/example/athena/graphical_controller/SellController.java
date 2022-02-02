package com.example.athena.graphical_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;

public class SellController {

    private final SceneSwitcher switcher = new SceneSwitcher();

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "bookshop-choose-view.fxml");
    }

    public void onSellBtnClick() throws IOException{
        switcher.popup("sellBookModule.fxml", "Sell a book") ;
    }
}
