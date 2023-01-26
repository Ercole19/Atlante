package com.example.athena.view.oracle_view;

import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class OnSelectTypeView {

    private final Parent root ;
    private final ChoiceBox<String> choiceBox ;

    private final Button confirm ;

    public OnSelectTypeView() {
        VBox box = new VBox();
        Label label = LabelBuilder.buildLabel("What kind of event are you adding ?") ;
        this.choiceBox = new ChoiceBox<>();

        confirm = new Button("Confirm") ;

        box.getChildren().add(label) ;
        box.getChildren().add(choiceBox) ;
        box.getChildren().add(confirm) ;

        this.root = box ;
    }

    public Parent getRoot() {
        return this.root ;
    }

    public ChoiceBox<String> getChoiceBox() {
        return this.choiceBox ;
    }

    public Button getConfirm() {
        return confirm;
    }
}
