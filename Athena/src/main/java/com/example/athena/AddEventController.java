package com.example.athena;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddEventController
{
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void clickOnAddEvent(ActionEvent event) throws IOException
    {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        stage.close() ;
    }

    public void clickOnX(ActionEvent event) throws IOException
    {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        stage.close() ;
    }
}
