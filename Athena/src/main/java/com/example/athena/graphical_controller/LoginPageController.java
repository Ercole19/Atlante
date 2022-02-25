package com.example.athena.graphical_controller;

import com.example.athena.entities.User;
import com.example.athena.use_case_controllers.LoginUseCaseControlller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;


public class LoginPageController {

    private int totalAttempts = 0 ;
    private int waitTimeMultiplier = 1;

    private final SceneSwitcher switcher = new SceneSwitcher();

    private Stage stage;

    @FXML
    private Button loginButton;
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
        bean = controlller.findUser(bean);

        if (bean.isUserFound()) {

            if (bean.getRole().equals("student")) {
                Alert alert = new Alert(Alert.AlertType.NONE, "Access granted !", ButtonType.CLOSE);
                alert.showAndWait();
                SceneSwitcher switcher = new SceneSwitcher();
                switcher.switcher(event, "MainPageStudents.fxml");
                User.getUser().setEmail(email);

            } else {
                Alert alert = new Alert(Alert.AlertType.NONE, "Access granted !", ButtonType.CLOSE);
                alert.showAndWait();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
                switcher.switcher(stage, "MainPageTutor.fxml");
                User.getUser().setEmail(email);
            }
        }

        else {
            if(totalAttempts < 5)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Access failed !", ButtonType.CLOSE);
                alert.showAndWait();
                totalAttempts++;
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Too many failed attempts, wait " + 10*waitTimeMultiplier + " seconds!", ButtonType.CLOSE);
                alert.showAndWait();
                new Thread(() -> {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            loginButton.setDisable(true);
                        }
                    });
                    try {
                        Thread.sleep(10000L * waitTimeMultiplier);
                    }
                    catch(InterruptedException ex) {
                    }
                    Platform.runLater(new Runnable() {
                        public void run() {
                            loginButton.setDisable(false);
                        }

                    });
                }).start();
                waitTimeMultiplier++;
            }
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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "MainPageStudents.fxml");
    }

    public void fillFast(ActionEvent event) {
        emailField.setText("alba@student.it");
        passField.setText("tramonto");
    }
}
