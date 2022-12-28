package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class EntryPoint extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        System.setProperty("oracle", "true");
        switcher.pushStage(stage);
        switcher.switcher("mainView.fxml");
        stage.setTitle("Athena");
        Image icon = new Image(new File("src/main/resources/assets/icon.png").toURI().toString());
        stage.getIcons().add(icon);
        stage.show();
    }

    public static void main(String[] args) {
        launch() ;
        System.exit(0) ;
    }
}