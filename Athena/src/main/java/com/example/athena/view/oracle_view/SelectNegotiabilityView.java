package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;
import com.example.athena.graphical_controller.oracle_interface.OracleSellBookGC;
import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SelectNegotiabilityView {

    private final Parent root;
    private final CheckBox checkBox;
    private final OracleSellBookGC controller;

    public SelectNegotiabilityView(OracleSellBookGC controller) {
        this.controller = controller;
        this.checkBox = new CheckBox();
        this.checkBox.setText("Negotiable");
        VBox vbox = new VBox();
        Button confirm = new Button("Confirm");
        Label choice = LabelBuilder.buildLabel("Do you want to set price negotiable ?");

        confirm.setOnAction(event -> {
            sendNegotiability();
        });

        vbox.getChildren().add(checkBox);
        vbox.getChildren().add(choice);
        vbox.getChildren().add(confirm);

        this.root = vbox;
    }

    public Parent getRoot() {return this.root;}

    private void sendNegotiability() {this.controller.receiveNegotiability(this.checkBox.isSelected());}
}
