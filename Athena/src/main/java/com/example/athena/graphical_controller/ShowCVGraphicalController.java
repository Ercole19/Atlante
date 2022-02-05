package com.example.athena.graphical_controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowCVGraphicalController implements Initializable, PostInitialize
{
    @FXML
    private WebView cvWebView ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    @Override
    public void postInitialize(ArrayList<Object> params) {
        WebEngine engine = cvWebView.getEngine() ;
        engine.load("file://" + System.getProperty("user.dir") + "/src/main/resources/assets/" + params.get(0)) ;
    }
}
