package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.graphical_controller.AthenaApplication;
import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;

public class OracleApplication extends AthenaApplication {

    @Override
    protected void prepareInterface() {
        System.setProperty("oracle", "true") ;
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        switcher.switcher("mainView.fxml");
    }


    public static void main(String[] args) {
        launch() ;
        System.exit(0) ;
    }
}