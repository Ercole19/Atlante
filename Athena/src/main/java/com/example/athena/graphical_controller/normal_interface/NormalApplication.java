package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.graphical_controller.AthenaApplication;

public class NormalApplication extends AthenaApplication {

    @Override
    protected void prepareInterface() {
        System.setProperty("oracle", "false");
        SceneSwitcher switcher = SceneSwitcher.getInstance() ;
        switcher.switcher("LoginPage.fxml") ;
    }

    public static void main(String[] args) {
        launch();
        System.exit(0);
    }
}