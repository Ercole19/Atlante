package com.example.athena.view.oracle_view;

import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;

public class InsertPasswordView {

    private final Parent root ;
    private final PasswordField passwordField ;

    private final Button confirm ;

    private final Label nameLabel ;

    public InsertPasswordView() {
        VBox box = new VBox() ;

        this.nameLabel = LabelBuilder.buildLabel("") ;

        this.passwordField = new PasswordField() ;

        this.confirm = new Button("Confirm") ;

        box.getChildren().addAll(nameLabel, passwordField, confirm) ;

        this.root = box ;
    }

    public Parent getRoot() {
        return root;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getConfirm() {
        return confirm;
    }

    public Label getNameLabel() {
        return nameLabel;
    }
}
