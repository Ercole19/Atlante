package com.example.athena.exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class SizedAlert extends Alert {

    public SizedAlert(AlertType alertType, String s, double height, double width, ButtonType... buttonTypes) {
        this(alertType, s, buttonTypes) ;
        this.setHeight(height) ;
        this.setWidth(width) ;
    }

    public SizedAlert(AlertType alertType, String s, ButtonType... buttonTypes)
    {
        super(alertType, s, buttonTypes) ;
    }
}
