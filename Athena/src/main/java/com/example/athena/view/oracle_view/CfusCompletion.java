package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OracleTakenExamsGC;
import com.example.athena.graphical_controller.oracle_interface.ShowCfusCompletionGC;
import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class CfusCompletion {
    private final ShowCfusCompletionGC controller;
    private final Parent root ;
    private final Label gainedCfus;
    private final Label maxCfus;

    public CfusCompletion() {
        this.controller = new ShowCfusCompletionGC();
        this.root = new HBox();
        this.gainedCfus = LabelBuilder.buildLabel("") ;
        Label slash = LabelBuilder.buildLabel("/") ;
        this.maxCfus = LabelBuilder.buildLabel("");
        ((HBox)this.root).getChildren().addAll(gainedCfus, slash,  maxCfus) ;
    }

    public Parent getParent() {
        String[] result = this.controller.getCfusInfos();
        this.gainedCfus.setText(result[0]);
        this.maxCfus.setText(result[1]);
        return this.root ;
    }

}
