package com.example.athena.GraphicalController;

import com.example.athena.View.studentdao;
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
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class LoginPageController {

    private Parent root;
    private Stage stage;
    private Scene scene;
    private studentdao stDAO ;
    @FXML
    private TextField emailField ;
    @FXML
    private TextField passField;

    public void switchToMainPage(ActionEvent event) throws IOException
    {
        String email = emailField.getText() ;
        String password = passField.getText() ;
        stDAO = new studentdao() ;
        if (stDAO.findStudent(email , password)) {
            Alert alert = new Alert(Alert.AlertType.NONE , "Access granted !" , ButtonType.CLOSE) ;
            alert.showAndWait() ;
            SceneSwitcher switcher = new SceneSwitcher() ;
            switcher.switcher(event , "MainPageStudents.fxml");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR , "Access failed !" , ButtonType.CLOSE) ;
            alert.showAndWait() ;
        }
    }

    public void switchtoTutorMainPage(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageTutor.fxml");
    }

    public void switchToSignUpPage(ActionEvent event ) throws IOException {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "signUpView.fxml");

    }

    public void switchFast(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageStudents.fxml");
    }
}
