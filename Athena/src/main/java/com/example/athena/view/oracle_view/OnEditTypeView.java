package com.example.athena.view.oracle_view;

import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OnEditTypeView {

    private final Parent root ;

    private final ChoiceBox<String> choiceBox ;

    private final Button doneButton ;

    public OnEditTypeView() {
        VBox finalRoot = new VBox() ;

        Label mainText = LabelBuilder.buildLabel("Edit activity type:") ;
        choiceBox = new ChoiceBox<>() ;
        doneButton = new Button("Done") ;

        finalRoot.getChildren().addAll(mainText, choiceBox, doneButton) ;
        this.root = finalRoot ;
    }

    public Parent getRoot() {
        return root;
    }

    public ChoiceBox<String> getChoiceBox() {
        return choiceBox;
    }

    public Button getDoneButton() {
        return doneButton;
    }
}
