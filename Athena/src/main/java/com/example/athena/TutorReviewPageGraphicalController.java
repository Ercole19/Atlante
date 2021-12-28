package com.example.athena;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class TutorReviewPageGraphicalController implements Initializable
{
    @FXML
    private Label starsNumber ;

    @FXML
    private Label reviewCode ;

    @FXML
    private Label resultMessage ;

    @FXML
    private TextField studentUsername ;

    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        Parent root = load(Objects.requireNonNull(getClass().getResource("MainPageTutor.fxml")));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        starsNumber.setText("5*") ;
    }

    public void generateReviewCode()
    {
        String username = studentUsername.getText() ;
        resultMessage.setText("Here is your review code") ;
        reviewCode.setText(TutorReviewCodesGenerator.generateReviewCode(5)) ;
    }
}
