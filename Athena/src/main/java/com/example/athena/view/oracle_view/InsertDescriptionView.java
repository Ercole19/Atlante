package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;
import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class InsertDescriptionView {

    private Parent root ;
    private TextArea desc ;

    public InsertDescriptionView(OracleAddEventGC controller) {
        VBox box = new VBox() ;
        box.getChildren().add(LabelBuilder.buildLabel("Insert Description")) ;
        this.desc = new TextArea() ;
        box.getChildren().add(this.desc) ;
        Button button = new Button("Confirm") ;

        button.setOnAction(event -> controller.obtainDescription(this)) ;
        box.getChildren().add(button) ;

        this.root = box ;

    }

    public String getDescription() {
        return this.desc.getText() ;
    }

    public Parent getRoot() {
        return this.root ;
    }
}
