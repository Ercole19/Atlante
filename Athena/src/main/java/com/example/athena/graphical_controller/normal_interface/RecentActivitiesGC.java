package com.example.athena.graphical_controller.normal_interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RecentActivitiesGC {

    private final SceneSwitcher switcher = SceneSwitcher.getInstance();

    public void onRecentPurchasesBtnClick(){
        switcher.switcher("recentPurchasesScreen.fxml");
    }

    @FXML
    protected void onHomeButtonClick() {
        switcher.switcher("MainPageStudents.fxml");
    }

    @FXML
    protected void onBackButtonClick() {
        switcher.switcher("bookshop-choose-view.fxml");
    }

    public void onRecentSoldItemsBtnClick() {
        switcher.switcher("recentSoldItems.fxml");
    }

    public void onPlacedBidsBtnClick() {switcher.switcher("PlacedBids.fxml");}
}
