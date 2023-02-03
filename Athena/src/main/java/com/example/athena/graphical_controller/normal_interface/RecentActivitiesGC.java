package com.example.athena.graphical_controller.normal_interface;

import javafx.fxml.FXML;

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
}
