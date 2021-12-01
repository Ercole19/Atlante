package com.example.athena;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class HelloApplication extends Application {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Athena");
        Image icon = new Image(new File("src/main/resources/assets/icon.png").toURI().toString());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public void logoutBtn(ActionEvent event) throws IOException {
        root = load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}