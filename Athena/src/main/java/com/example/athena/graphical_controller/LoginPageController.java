package com.example.athena.graphical_controller;

import com.example.athena.entities.User;
import com.example.athena.use_case_controllers.LoginUseCaseControlller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginPageController {



    @FXML
    private TextField emailField ;
    @FXML
    private TextField passField;

    public void switchToMainPage(ActionEvent event) throws IOException {
        String email = emailField.getText() ;
        String password = passField.getText() ;

        UserBean bean = new UserBean();
        bean.setEmail(email);
        bean.setPassword(password);

        LoginUseCaseControlller controlller = new LoginUseCaseControlller() ;

        if (controlller.findUser(bean)) {

            if (bean.getRole().equals("student")) {
                Alert alert = new Alert(Alert.AlertType.NONE, "Access granted !", ButtonType.CLOSE);
                alert.showAndWait();
                SceneSwitcher switcher = new SceneSwitcher();
                switcher.switcher(event, "MainPageStudents.fxml");
                User.getUser().setEmail(email);

            } else {
                Alert alert = new Alert(Alert.AlertType.NONE, "Access granted !", ButtonType.CLOSE);
                alert.showAndWait();
                SceneSwitcher switcher = new SceneSwitcher();
                switcher.switcher(event, "MainPageTutor.fxml");
                User.getUser().setEmail(email);
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

    public void switchFast(ActionEvent event) throws IOException {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageStudents.fxml");
    }

    public void fillFast(ActionEvent event) {
        emailField.setText("alba@student.it");
        passField.setText("tramonto");
    }
}
