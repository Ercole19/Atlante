package com.example.athena.graphical_controller.normal_interface;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class BlindApplication extends Application {

    @Override

    public void start(Stage stage) throws IOException {
        System.setProperty("fxmlType", "blindfxml");
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        switcher.pushStage(stage);
        switcher.switcher("blind_LoginPage.fxml");
        stage.setTitle("Athena");
        Image icon = new Image(new File("src/main/resources/assets/icon.png").toURI().toString());
        stage.getIcons().add(icon);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
        System.exit(0);
    }
}
