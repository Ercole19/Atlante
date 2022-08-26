package com.example.athena.graphical_controller;

import com.example.athena.view.RecentSoldItemsView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecentSoldItemsGC implements Initializable {

    @FXML
    private SubScene subScene;
    private RecentSoldItemsView recentSoldItemsView;

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        SceneSwitcher switcher = new SceneSwitcher() ;
        switcher.switcher(stage, "recentActivities.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recentSoldItemsView = new RecentSoldItemsView(subScene.getWidth(), subScene.getHeight());
        this.subScene.setRoot(recentSoldItemsView.getRoot());
    }

}
