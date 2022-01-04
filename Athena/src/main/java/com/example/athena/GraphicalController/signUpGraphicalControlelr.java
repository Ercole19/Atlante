package com.example.athena.GraphicalController;

import com.example.athena.View.userdao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class signUpGraphicalControlelr {
    private Parent root;
    private Stage stage;
    private Scene scene;
    private userdao stDAO;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passField;


    public void onBackButtonClick(ActionEvent event) throws IOException {

        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "LoginPage.fxml");
    }

    public void onConfirmButtonClick (ActionEvent event) throws IOException {
        String email = emailField.getText() ;
        String password = passField.getText() ;
        stDAO = new userdao() ;
        if (stDAO.registerUser(email , password)) {
            SceneSwitcher switcher = new SceneSwitcher();
            switcher.switcher(event, "LoginPage.fxml");
        }



        }



    }


