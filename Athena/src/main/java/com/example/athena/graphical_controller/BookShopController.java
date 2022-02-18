package com.example.athena.graphical_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;


public class BookShopController {

    SceneSwitcher switcher = new SceneSwitcher();

    @FXML
    protected void onHomeButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    @FXML
    protected void onBookBtnClick(ActionEvent event) throws IOException{
        switcher.switcher(event, "BuyBookPage.fxml");
    }

    public void onBuyButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "buy-view.fxml");
    }

    public void onSellButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "sell-view.fxml");
    }

    public void onRecentPurchasesBtnClick(ActionEvent event)throws IOException{
        switcher.switcher(event, "recentPurchasesScreen.fxml");
    }
}