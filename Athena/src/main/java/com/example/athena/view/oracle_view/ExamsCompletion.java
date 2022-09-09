package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OracleShowExamsCompletionGC;
import com.example.athena.graphical_controller.oracle_interface.ShowCfusCompletionGC;
import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ExamsCompletion {
    private final OracleShowExamsCompletionGC controller;
    private final Parent root ;
    private final Label takenExams;
    private final Label maxExams;

    public ExamsCompletion() {
        this.controller = new OracleShowExamsCompletionGC();
        this.root = new HBox();
        this.takenExams = LabelBuilder.buildLabel("") ;
        Label slash = LabelBuilder.buildLabel("/") ;
        this.maxExams = LabelBuilder.buildLabel("");
        ((HBox)this.root).getChildren().addAll(takenExams, slash,  maxExams) ;
    }

    public Parent getParent() {
        String[] result = this.controller.getExamsInformation();
        this.takenExams.setText(result[0]);
        this.maxExams.setText(result[1]);
        return this.root ;
    }

}
