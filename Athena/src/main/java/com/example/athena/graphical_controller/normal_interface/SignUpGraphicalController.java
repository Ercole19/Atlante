package com.example.athena.graphical_controller.normal_interface;


import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.exceptions.UserRegistrationException;
import com.example.athena.beans.UserBean;
import com.example.athena.use_case_controllers.SignUpUCC;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class SignUpGraphicalController {
    @FXML
    private TextField emailField;
    @FXML
    private TextField passField;
    @FXML
    private TextField nameField ;
    @FXML
    private TextField surnameField ;
    @FXML
    private TextField confirmPassField ;
    @FXML
    private RadioButton studentRadiobutton ;
    private final SceneSwitcher switcher = SceneSwitcher.getInstance();


    public void onBackButtonClick() throws IOException {
        switcher.switcher("LoginPage.fxml");
    }

    public void onConfirmButtonClick () {
        String userType;
        String email = emailField.getText() ;
        String password = passField.getText() ;
        String nome = nameField.getText() ;
        String cognome = surnameField.getText() ;
        String passConfirm = confirmPassField.getText() ;

        if (password.equals(passConfirm)) {

            if (studentRadiobutton.isSelected()) {userType = "STUDENT";}
            else {userType = "TUTOR";}

            UserBean bean = new UserBean();
            bean.setEmail(email);
            bean.setPassword(password);
            bean.setRole(userType);
            bean.setName(nome);
            bean.setSurname(cognome);

            SignUpUCC controller = new SignUpUCC();
            try
            {
                controller.preRegister(bean);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Registration successful", ButtonType.CLOSE);
                alert.showAndWait();
                ArrayList<Object> params = new ArrayList<>() ;
                params.add(emailField.getText()) ;
                switcher.switcher("validate_signup.fxml", params);
            }
            catch (UserRegistrationException | SendEmailException | NoSuchAlgorithmException exc)
            {
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, exc.getMessage());
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR , "You inserted two different passwords!") ;
            alert.showAndWait() ;
        }
    }

    public void onValidateCodeClick() {
        switcher.switcher("validate_signup.fxml");
    }
}