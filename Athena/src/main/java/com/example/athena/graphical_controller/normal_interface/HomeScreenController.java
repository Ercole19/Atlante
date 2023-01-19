package com.example.athena.graphical_controller.normal_interface;

import javafx.fxml.FXML;

public abstract class HomeScreenController {

    @FXML
    protected void onHomeButtonClick() {
        SceneSwitcher.getInstance().switcher("MainPageStudents.fxml");
    }
}
