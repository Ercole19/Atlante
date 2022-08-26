package com.example.athena.graphical_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class RecentActivitiesGC {

    private final SceneSwitcher switcher = new SceneSwitcher();
    private Stage stage;

    public void onRecentPurchasesBtnClick(ActionEvent event){
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "recentPurchasesScreen.fxml");
    }

    @FXML
    protected void onHomeButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "MainPageStudents.fxml");
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "bookshop-choose-view.fxml");
    }

    public void onRecentSoldItemsBtnClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "recentSoldItems.fxml");
    }


}
