package com.example.athena;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class LoginPageController {

    private Parent root;
    private Stage stage;
    private Scene scene;
    private studentDAO stDAO ;
    @FXML
    private TextField emailField ;
    @FXML
    private TextField passField;

    public void switchToMainPage(ActionEvent event) throws IOException
            //email = emailprova
            // pass = passprova
    {
        String email = emailField.getText() ;
        String password = passField.getText() ;
        stDAO = new studentDAO() ;
        if (stDAO.findStudent(email , password)) {
            Alert alert = new Alert(Alert.AlertType.NONE , "Access granted !" , ButtonType.CLOSE) ;
            alert.showAndWait();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainPageStudents.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR , "Access failed !" , ButtonType.CLOSE) ;
            alert.showAndWait() ;
        }
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

    public void switchFast(ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainPageStudents.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
