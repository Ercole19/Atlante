package com.example.athena.exceptions;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class SwitcherExceptionsHandler {
    public static void handleSwitcherException(Stage stage, String message)
    {
        SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, message, 800, 600) ;
        alert.showAndWait() ;
        stage.close() ;
    }
}
