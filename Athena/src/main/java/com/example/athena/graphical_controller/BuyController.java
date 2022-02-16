package com.example.athena.graphical_controller;

import com.example.athena.use_case_controllers.BuyControllerUCC;
import com.example.athena.view.ErrorSceneView;
import com.example.athena.view.SearchResultFormatterView;
import com.example.athena.view.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.view.scene_decorators.SearchResultFormatterScrollBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.List;

public class BuyController {
    SceneSwitcher switcher = new SceneSwitcher();

    @FXML
    protected void onHomeButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    @FXML
    protected void onSearchButtonClick(){
        //TODO

    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "bookshop-choose-view.fxml");
    }

}
