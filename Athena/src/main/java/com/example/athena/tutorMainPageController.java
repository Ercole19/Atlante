package com.example.athena;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class tutorMainPageController {

    HelloApplication Btn = new HelloApplication();
    private Parent root;
    private Stage stage;
    private Scene scene;


    public void onPersonalPageButtonClick(ActionEvent event) throws IOException {
        root = load(Objects.requireNonNull(getClass().getResource("tutorEditingPage.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

    }

    public void onLogoutButtonClick(ActionEvent event) throws IOException {
        Btn.logoutBtn(event);
    }

}
