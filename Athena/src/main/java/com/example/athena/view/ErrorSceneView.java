package com.example.athena.view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ErrorSceneView
{
    public AnchorPane createErrorScreen(String message, double width, double height)
    {
        AnchorPane error = new AnchorPane() ;
        error.setPrefWidth(width) ;
        error.setPrefHeight(height) ;
        Text display = new Text(message) ;
        error.getChildren().add(display) ;
        display.setLayoutX(error.getWidth()/100*45) ;
        display.setLayoutY(error.getHeight()/100*45) ;

        return error ;
    }
}
