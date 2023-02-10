package com.example.athena.view.oracle_view;

import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OnChooseSubjectView {

    private final Parent root ;
    private final ChoiceBox<String> choiceBox ;

    private final Button submit ;

    public OnChooseSubjectView() {

        VBox box = new VBox() ;

        Label title = LabelBuilder.buildLabel("Choose the subject of the tutoring") ;
        this.choiceBox = new ChoiceBox<>() ;

        submit = new Button("Generate review") ;

        box.getChildren().addAll(title, choiceBox, submit) ;

        this.root = box ;
    }

    public Parent getRoot() {
        return this.root ;
    }

    public ChoiceBox<String> getChoiceBox() {
        return this.choiceBox ;
    }

    public Button getSubmit() {
        return submit;
    }
}
