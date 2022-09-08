package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.exceptions.UserRegistrationException;
import com.example.athena.beans.normal.RegistrationBean;
import com.example.athena.use_case_controllers.SignUpUCC;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class ValidateSignUpGC implements PostInitialize {

    @FXML
    private TextField emailField ;

    @FXML
    private TextField registrationCodeField ;

    private final SceneSwitcher switcher = SceneSwitcher.getInstance();

    public void onBackButtonClick() throws IOException {
        switcher.switcher("LoginPage.fxml");
    }

    public void onConfirmClick() {
        SignUpUCC signUpUCC = new SignUpUCC() ;
        RegistrationBean bean = new RegistrationBean() ;
        bean.setEmail(emailField.getText());
        bean.setCode(registrationCodeField.getText());
        try {
            signUpUCC.register(bean) ;
            switcher.switcher("LoginPage.fxml");
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
