package com.example.athena.graphical_controller;


import com.example.athena.exceptions.SizedAlert;
import com.example.athena.exceptions.UserRegistrationException;
import com.example.athena.use_case_controllers.SignUpUCC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
    private final SceneSwitcher switcher = new SceneSwitcher();
    private Stage stage;


    public void onBackButtonClick(ActionEvent event) throws IOException {

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "LoginPage.fxml");
    }

    public void onConfirmButtonClick (ActionEvent event) {

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
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                ArrayList<Object> params = new ArrayList<>() ;
                params.add(emailField.getText()) ;
                switcher.switcher(stage, "validate_signup.fxml", params);
            }
            catch (UserRegistrationException exc)
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

    public void onValidateCodeClick(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        switcher.switcher(stage, "validate_signup.fxml");
    }
}