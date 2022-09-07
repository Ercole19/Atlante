package com.example.athena.graphical_controller.normal_interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;


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
        switcher.switcher(stage, "buy-view2.fxml");
    }

    public void onSellButtonClick(ActionEvent event){
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "sell-view.fxml");
    }

    public void onRecentActivitiesBtnClick(ActionEvent event){
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "recentActivities.fxml");
    }
}