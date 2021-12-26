package com.example.athena;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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
        root = load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    public void onConfirmButtonClick (ActionEvent event) throws IOException {
        String email = emailField.getText() ;
        String password = passField.getText() ;
        stDAO = new studentdao() ;
        if (!(stDAO.findStudent(email , password))) {
            stDAO.registerUser(email , password);
            Alert alert =new Alert(Alert.AlertType.CONFIRMATION , "Registration Successful" , ButtonType.CLOSE) ;
            root = load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR , "Email already used"  , ButtonType.CLOSE) ;
            alert.showAndWait() ;
            return;

        }



    }

}
