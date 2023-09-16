package com.example.athena.view.oracle_view;

import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class OnSetReminderView {

    private Parent root ;
    private TextField field ;
    private Button button ;

    private Label errorLabel ;

    public OnSetReminderView() {
        VBox box = new VBox() ;
        box.getChildren().add(LabelBuilder.buildLabel("Possible choices:\n" +
                "\tHalf an hour before;\n" +
                "\tAn hour before;\n" +
                "\tHalf and an hour before;\n" +
                "\tTwo hours before;\n" +
                "\tOne day before;\n" +
                "\thhhh:mm")) ;
        field = new TextField() ;
        button = new Button("Confirm") ;

        box.getChildren().addAll(field, button) ;

        errorLabel = LabelBuilder.buildLabel("") ;
        box.getChildren().add(errorLabel) ;

        this.root = box ;
    }

    public Parent getRoot() {
        return this.root;
    }

    public TextField getField() {
        return field;
    }

    public Button getButton() {
        return button;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
}
