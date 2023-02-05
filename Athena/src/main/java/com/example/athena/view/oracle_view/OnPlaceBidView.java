package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OracleBookPageGC;
import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class OnPlaceBidView {

    private final Parent root;
    private TextField field;
    private final OracleBookPageGC controller;

    public OnPlaceBidView(OracleBookPageGC controller) {
        this.controller = controller;
        this.field = new TextField();
        VBox vbox = new VBox();
        Button confirm = new Button("Confirm");
        Label choice = LabelBuilder.buildLabel("Insert your bid");

        confirm.setOnAction(event -> sendBid());

        vbox.getChildren().add(choice);
        vbox.getChildren().add(field);
        vbox.getChildren().add(confirm);
        vbox.setSpacing(30);

        this.root = vbox;
    }

    public Parent getRoot() {return this.root;}
    public TextField getField() {return this.field;}
    private void sendBid() {this.controller.sendBid(this);}
}
