package com.example.athena.graphical_controller;

import com.example.athena.entities.Student;
import com.example.athena.entities.User;
import com.example.athena.exceptions.BookException;
import com.example.athena.use_case_controllers.RecentPurchaseUCC;
import com.example.athena.view.RecentPurchasesView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RecentPurchasesGC implements Initializable {

    @FXML
    private SubScene subScene;
    private RecentPurchasesView recentPurchasesView = new RecentPurchasesView(subScene.getWidth(), subScene.getHeight());

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        SceneSwitcher switcher = new SceneSwitcher() ;
        switcher.switcher(stage, "bookshop-choose-view.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.subScene.setRoot(recentPurchasesView.getRoot());
    }
}
