package com.example.athena.view.oracle_view;

import com.example.athena.entities.ActivityTypesEnum;
import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;
import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class SelectTypeView {

    private final Parent root ;
    private final ChoiceBox<String> choiceBox ;
    private final OracleAddEventGC controller ;

    public SelectTypeView(OracleAddEventGC controller) {
        this.controller = controller ;
        VBox box = new VBox();
        Label label = LabelBuilder.buildLabel("What kind of event are you adding ?") ;
        this.choiceBox = new ChoiceBox<>();
        for (ActivityTypesEnum elem : ActivityTypesEnum.values()) {
            String text = elem.toString().charAt(0) + elem.toString().substring(1).toLowerCase().replace('_', ' ') ;
            this.choiceBox.getItems().add(text);
        }

        Button confirm = new Button("Confirm") ;
        confirm.setOnAction(event ->
            sendEventType()
        ) ;

        box.getChildren().add(label) ;
        box.getChildren().add(choiceBox) ;
        box.getChildren().add(confirm) ;

        this.root = box ;
    }

    private void sendEventType() {
        this.controller.obtainEventType(this) ;
    }

    public Parent getRoot() {
        return this.root ;
    }

    public ChoiceBox<String> getChoiceBox() {
        return this.choiceBox ;
    }
}
