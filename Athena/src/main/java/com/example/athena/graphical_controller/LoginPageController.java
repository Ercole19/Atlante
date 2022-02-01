package com.example.athena.graphical_controller;

import com.example.athena.entities.User;
import com.example.athena.entities.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import java.io.IOException;


public class LoginPageController {

    private User currentuser ;

    @FXML
    private TextField emailField ;
    @FXML
    private TextField passField;

    public void switchToMainPage(ActionEvent event) throws IOException
    {
        String email = emailField.getText() ;
        String password = passField.getText() ;
        UserDao stDAO = new UserDao() ;
        if (stDAO.findStudent(email , password)) {

            if (stDAO.getuserType(email).equals("student")) {
                Alert alert = new Alert(Alert.AlertType.NONE, "Access granted !", ButtonType.CLOSE);
                alert.showAndWait();
                SceneSwitcher switcher = new SceneSwitcher();
                switcher.switcher(event, "MainPageStudents.fxml");
                currentuser = currentuser.getUser();
                currentuser.setEmail(email);
            } else {
                Alert alert = new Alert(Alert.AlertType.NONE, "Access granted !", ButtonType.CLOSE);
                alert.showAndWait();
                SceneSwitcher switcher = new SceneSwitcher();
                switcher.switcher(event, "MainPageTutor.fxml");
                currentuser = currentuser.getUser();
                currentuser.setEmail(email);

            }


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
