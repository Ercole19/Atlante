package com.example.athena;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class signUpGraphicalControlelr {
    private Parent root;
    private Stage stage;
    private Scene scene;
    private studentDAO stDAO;
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
        stDAO = new studentDAO() ;
        stDAO.registerUser(email , password );
        root = load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);


    }

}
