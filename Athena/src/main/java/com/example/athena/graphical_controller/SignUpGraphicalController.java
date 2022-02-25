package com.example.athena.graphical_controller;


import com.example.athena.use_case_controllers.SignUpUCC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


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

    public void onConfirmButtonClick (ActionEvent event) throws IOException {

        String email = emailField.getText() ;
        String password = passField.getText() ;
        String nome = nameField.getText() ;
        String cognome = surnameField.getText() ;
        String passConfirm = confirmPassField.getText() ;

        if (password.equals(passConfirm)) {
            String userType;
            if (studentRadiobutton.isSelected()) {
                userType = "student" ;

            }
            else {
                userType = "tutor" ;
            }

            UserBean bean = new UserBean();
            bean.setEmail(email);
            bean.setPassword(password);
            bean.setRole(userType);
            bean.setName(nome);
            bean.setSurname(cognome);

            SignUpUCC controller = new SignUpUCC();

            if (controller.register(bean)) {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
                switcher.switcher(stage, "LoginPage.fxml");
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR , "Data not valid") ;
            alert.showAndWait() ;
        }



        }



    }


