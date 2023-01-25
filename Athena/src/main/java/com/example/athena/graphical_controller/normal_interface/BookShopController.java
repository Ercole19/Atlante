package com.example.athena.graphical_controller.normal_interface;

import javafx.fxml.FXML;


public class BookShopController extends HomeScreenController {

    private final SceneSwitcher switcher = SceneSwitcher.getInstance();

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