package com.example.athena.view.oracle_view;

import com.example.athena.view.LabelBuilder;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SignUpView {
    private Parent confirmParent ;

    public SignUpView() {
        this.confirmParent = prepareConfirmPArent() ;
    }

    private Parent prepareConfirmPArent() {
        HBox box = new HBox();
        Label label = LabelBuilder.buildLabel("Confirm password");
        TextField textField = new TextField();
        Button button = new Button("Confirm");
        textField.setId("confirmTextfield");
        button.setId("confirmButton");
        box.getChildren().addAll(label, textField, button);
        return box;
    }

    public Parent getParent() {
        return this.confirmParent ;
    }

    public Node lookup(String id) {
        return this.confirmParent.lookup(id) ;
    }
}
