package com.example.athena.graphical_controller;

import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public abstract class AthenaApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SceneSwitcher switcher = SceneSwitcher.getInstance() ;
        stage.setResizable(false) ;
        switcher.pushStage(stage) ;
        prepareInterface() ;
        stage.setTitle("Athena");
        Image icon = new Image(new File("src/main/resources/assets/icon.png").toURI().toString());
        stage.getIcons().add(icon);
        stage.show();
    }

    protected abstract void prepareInterface() ;
}
