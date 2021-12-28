package com.example.athena.GraphicalController;

import com.example.athena.View.studentdao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class signUpGraphicalControlelr {
    private Parent root;
    private Stage stage;
    private Scene scene;
    private studentdao stDAO;
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
        stDAO = new studentdao() ;
        if (!(stDAO.findStudent(email , password))) {
            stDAO.registerUser(email , password);
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION , "Registration Successful" , ButtonType.CLOSE) ;
            SceneSwitcher switcher = new SceneSwitcher();
            switcher.switcher(event, "LoginPage.fxml");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR , "Email already used"  , ButtonType.CLOSE) ;
            alert.showAndWait() ;
            return;

        }



    }

}
