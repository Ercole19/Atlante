package com.example.athena.GraphicalController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class sellController {
    SceneSwitcher switcher = new SceneSwitcher();

    @FXML
    protected void onHomeButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "bookshop-choose-view.fxml");
    }

}
