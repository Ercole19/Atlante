package com.example.athena.GraphicalController;

import com.example.athena.View.userdao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class signUpGraphicalControlelr {
    private userdao stDAO;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passField;
    @FXML
    private TextField confirmEmailField ;
    @FXML
    private TextField confirmPassField ;
    @FXML
    private RadioButton studentRadiobutton ;
    @FXML
    private RadioButton tutorRadiobutton ;
    private String userType ;


    public void onBackButtonClick(ActionEvent event) throws IOException {

        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "LoginPage.fxml");
    }

    public void onConfirmButtonClick (ActionEvent event) throws IOException {
        String email = emailField.getText() ;
        String password = passField.getText() ;
        String emailConfirm = confirmEmailField.getText() ;
        String passConfirm = confirmPassField.getText() ;
        if (email.equals(emailConfirm) & password.equals(passConfirm)) {
            if (studentRadiobutton.isSelected()) {
                userType = "student" ;

            }
            else {
                userType = "tutor" ;
            }
            stDAO = new userdao();
            if (stDAO.registerUser(email, password , userType)) {
                SceneSwitcher switcher = new SceneSwitcher();
                switcher.switcher(event, "LoginPage.fxml");
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR , "Data not valid") ;
            alert.showAndWait() ;
        }



        }



    }


