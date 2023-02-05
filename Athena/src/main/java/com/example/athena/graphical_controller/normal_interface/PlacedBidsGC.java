package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.view.PlacedBidsView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;

import java.net.URL;
import java.util.ResourceBundle;

public class PlacedBidsGC implements Initializable {
    @FXML
    private SubScene subScene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PlacedBidsView view = new PlacedBidsView(subScene.getWidth(), subScene.getHeight()) ;
        subScene.setRoot(view.getRoot());
    }

    @FXML
    protected void onBackButtonClick() {
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        switcher.switcher("recentActivities.fxml");
    }

}