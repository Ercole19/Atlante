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

    private Button confirm ;

    public InsertDescriptionView() {
        VBox box = new VBox() ;
        box.getChildren().add(LabelBuilder.buildLabel("Insert Description")) ;
        this.desc = new TextArea() ;
        box.getChildren().add(this.desc) ;
        confirm = new Button("Confirm") ;

        box.getChildren().add(confirm) ;

        this.root = box ;
    }

    public TextArea getDesc() {
        return this.desc ;
    }

    public Parent getRoot() {
        return this.root ;
    }

    public Button getConfirm() {
        return confirm;
    }
}
