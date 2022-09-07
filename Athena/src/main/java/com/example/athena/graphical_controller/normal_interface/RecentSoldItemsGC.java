package com.example.athena.graphical_controller.normal_interface;

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

    @FXML
    protected void onBackButtonClick() throws IOException {
        SceneSwitcher switcher = SceneSwitcher.getInstance() ;
        switcher.switcher("recentActivities.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RecentSoldItemsView recentSoldItemsView = new RecentSoldItemsView(subScene.getWidth(), subScene.getHeight());
        this.subScene.setRoot(recentSoldItemsView.getRoot());
    }

}
