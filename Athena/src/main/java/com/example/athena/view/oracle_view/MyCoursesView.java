package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import javafx.scene.Parent;

public class MyCoursesView {

    public Parent createParent() {
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        return switcher.preload("oracleMyCourses.fxml");
    }
}

