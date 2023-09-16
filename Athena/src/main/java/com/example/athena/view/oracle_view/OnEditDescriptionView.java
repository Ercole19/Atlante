package com.example.athena.view.oracle_view;

import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class OnEditDescriptionView {

    private final Parent root ;

    private final TextArea descriptionArea;

    private final Button doneButton ;

    public OnEditDescriptionView() {
        VBox finalRoot = new VBox() ;

        Label mainText = LabelBuilder.buildLabel("Edit event description") ;

        descriptionArea = new TextArea() ;

        doneButton = new Button("Done") ;

        finalRoot.getChildren().addAll(mainText, descriptionArea, doneButton) ;

        this.root = finalRoot ;
    }

    public Parent getRoot() {
        return root;
    }

    public TextArea getDescriptionArea() {
        return descriptionArea;
    }

    public Button getDoneButton() {
        return doneButton;
    }
}
