package com.example.atena;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class EsamiHomepage extends Application {


    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Carriera-view.fxml"));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("ATENA");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}