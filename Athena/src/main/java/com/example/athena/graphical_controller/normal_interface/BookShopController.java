package com.example.athena.graphical_controller.normal_interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;


public class BookShopController {

    private final SceneSwitcher switcher = SceneSwitcher.getInstance();

    @FXML
    protected void onHomeButtonClick() {
        switcher.switcher("MainPageStudents.fxml");
    }

    @FXML
    protected void onBackButtonClick() {
        switcher.switcher("MainPageStudents.fxml");
    }

    @FXML
    protected void onBookBtnClick(){
        switcher.switcher("BuyBookPage.fxml");
    }

    public void onBuyButtonClick(){
        switcher.switcher("buy-view2.fxml");
    }

    public void onSellButtonClick(){
        switcher.switcher("sell-view.fxml");
    }

    public void onRecentActivitiesBtnClick(){
        switcher.switcher("recentActivities.fxml");
    }
}