package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;
import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SetReminderView {

    private Parent root ;
    private TextField field ;
    private Button button ;

    public SetReminderView(OracleAddEventGC controller) {
        VBox box = new VBox() ;
        box.getChildren().add(LabelBuilder.buildLabel("Possible choices:\n" +
                "An hour before;\n" +
                "..\n" +
                "hhhh:mm")) ;
        field = new TextField() ;
        button = new Button("Confirm") ;

        box.getChildren().addAll(field, button) ;
        button.setOnAction(event -> controller.obtainReminder());
    }


    public Parent getRoot() {
        return this.root;
    }

}
