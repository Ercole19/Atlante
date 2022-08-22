package com.example.athena.graphical_controller;

import com.example.athena.exceptions.UserRegistrationException;
import com.example.athena.use_case_controllers.SignUpUCC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ValidateSignUpGC implements PostInitialize {

    @FXML
    private TextField emailField ;

    @FXML
    private TextField registrationCodeField ;

    private final SceneSwitcher switcher = new SceneSwitcher();
    private Stage stage;

    public void onBackButtonClick(ActionEvent event) throws IOException {

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "LoginPage.fxml");
    }

    public void onConfirmClick(ActionEvent event) {
        SignUpUCC signUpUCC = new SignUpUCC() ;
        RegistrationBean bean = new RegistrationBean() ;
        bean.setEmail(emailField.getText());
        bean.setCode(registrationCodeField.getText());
        try {
            signUpUCC.register(bean) ;
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            switcher.switcher(stage, "LoginPage.fxml");
        } catch (UserRegistrationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage()) ;
            alert.showAndWait() ;
        }
    }

    @Override
    public void postInitialize(ArrayList<Object> params) {
        emailField.setText((String) params.get(0)) ;
    }
}
