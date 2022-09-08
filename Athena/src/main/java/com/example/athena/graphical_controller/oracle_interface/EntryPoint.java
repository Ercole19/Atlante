package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import javafx.application.Application;
import javafx.stage.Stage;

public class EntryPoint extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        System.setProperty("fxmlType", "fxml");
        System.out.println(System.getProperty("fxmlType"));
        switcher.pushStage(stage);
        switcher.switcher("mainView.fxml");
        stage.show();
    }

    public static void main(String[] args) {
        launch() ;
    }
}
