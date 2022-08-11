package com.example.athena.graphical_controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class EventDescriptionGC implements PostInitialize{
    @FXML
    private Text description;

    @Override
    public void postInitialize(ArrayList<Object> params) {
        description.setText(params.get(0).toString());
    }
}
