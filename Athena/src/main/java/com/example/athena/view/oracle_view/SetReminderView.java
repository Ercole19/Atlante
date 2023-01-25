package com.example.athena.view.oracle_view;

import com.example.athena.exceptions.EventException;
import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;
import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SetReminderView {

    private Parent root ;
    private TextField field ;
    private Button button ;

    private Label errorLabel ;

    public SetReminderView(OracleAddEventGC controller) {
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
        button.setOnAction(event -> {
                try {
                    controller.obtainReminder(this) ;
                } catch (EventException e) {
                    setErrorLabel(e.getMessage());
                    cleanTextField();
                }
        });

        errorLabel = LabelBuilder.buildLabel("") ;
        box.getChildren().add(errorLabel) ;

        this.root = box ;
    }

    public String getReminderString() {
        return this.field.getText() ;
    }

    private void setErrorLabel(String text) {
        errorLabel.setText(text) ;
    }

    private void cleanTextField() {
        this.field.clear();
    }


    public Parent getRoot() {
        return this.root;
    }

}
