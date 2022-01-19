package com.example.athena.GraphicalController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;


public class BookShopController {

    SceneSwitcher switcher = new SceneSwitcher();

    @FXML
    protected void onSettingsButtonClick(ActionEvent event) {
        Scene scene = ((Node) event.getSource()).getScene();
        WebEngine webEngine = ((WebView)scene.lookup("#webComponent")).getEngine();
        webEngine.load("Athena/src/main/resources/com/example/athena/fxml/openLibAPI.html");
        System.out.println(4);}

    @FXML
    protected void onHomeButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    @FXML
    protected void onSearchButtonClick(){

    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    @FXML
    protected void onBookBtnClick(ActionEvent event) throws IOException{
        switcher.switcher(event, "BuyBookPage.fxml");
    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "LoginPage.fxml");
    }

    public void onBuyButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "buy-view.fxml");
    }

    public void onSellButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "sell-view.fxml");
    }
}