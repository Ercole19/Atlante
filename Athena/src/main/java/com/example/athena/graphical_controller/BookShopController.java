package com.example.athena.graphical_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;


public class BookShopController {

    private final SceneSwitcher switcher = new SceneSwitcher();
    private Stage stage;

    @FXML
    protected void onHomeButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "MainPageStudents.fxml");
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "MainPageStudents.fxml");
    }

    @FXML
    protected void onBookBtnClick(ActionEvent event){
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "BuyBookPage.fxml");
    }

    public void onBuyButtonClick(ActionEvent event){
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "buy-view.fxml");
    }

    public void onSellButtonClick(ActionEvent event){
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "sell-view.fxml");
    }

    public void onRecentPurchasesBtnClick(ActionEvent event){
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "recentPurchasesScreen.fxml");
    }
}