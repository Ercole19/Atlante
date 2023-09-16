package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.view.RecentPurchasesView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecentPurchasesPageGC implements Initializable {

    @FXML
    private SubScene subScene;

    @FXML
    protected void onBackButtonClick() throws IOException {
        SceneSwitcher switcher = SceneSwitcher.getInstance() ;
        switcher.switcher("recentActivities.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RecentPurchasesView recentPurchasesView = new RecentPurchasesView(subScene.getWidth(), subScene.getHeight());
        this.subScene.setRoot(recentPurchasesView.getRoot());
    }
}
