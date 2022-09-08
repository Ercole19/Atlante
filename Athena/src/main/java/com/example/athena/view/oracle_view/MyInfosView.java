package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import javafx.scene.Parent;

public class MyInfosView {
    public Parent prepareView() {
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        return switcher.preload("oracleMyInfos.fxml");
    }
}
