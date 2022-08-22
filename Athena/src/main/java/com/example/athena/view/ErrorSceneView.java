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
        display.setLayoutX(width/100*45) ;
        display.setLayoutY(height/100*45) ;
        error.getChildren().add(display) ;

        return error ;
    }
}
