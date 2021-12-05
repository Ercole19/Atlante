package com.example.athena;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class LoginPageController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void switchToMainPage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainPageStudents.fxml"))) ;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene) ;
    }

    public void switchtoTutorMainPage(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainPageTutor.fxml"))) ;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene) ;
    }

    public void switchToSignUpPage(ActionEvent event ) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signUpView.fxml"))) ;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene) ;

    }
}
