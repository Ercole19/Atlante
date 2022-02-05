package com.example.athena.graphical_controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowCVGraphicalController implements Initializable
{
    @FXML
    private WebView cvWebView ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        WebEngine engine = cvWebView.getEngine() ;
        engine.load("file://" + System.getProperty("User.dir") + "/src/main/resources/assets/CV.html") ;
    }
}
