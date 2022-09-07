package com.example.athena.graphical_controller.normal_interface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    protected void onBackButtonClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "bookshop-choose-view.fxml");
    }

    public void onRecentSoldItemsBtnClick() {
        switcher.switcher("recentSoldItems.fxml");
    }


}
