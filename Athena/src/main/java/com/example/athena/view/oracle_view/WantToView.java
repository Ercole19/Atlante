package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OnYesOrNoController;
import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WantToView {

    private Parent root ;

    public WantToView(String message, OnYesOrNoController controller) {
        VBox box = new VBox() ;
        box.getChildren().add(LabelBuilder.buildLabel(message)) ;
        HBox yesOrNo = new HBox() ;
        box.getChildren().add(yesOrNo) ;

        Button yesButton = new Button("Yes") ;
        yesButton.setOnAction(event -> controller.onYes());
        yesOrNo.getChildren().add(yesButton) ;

        Button noButton = new Button("No") ;
        noButton.setOnAction(event -> controller.onNo());
        yesOrNo.getChildren().add(noButton) ;

        this.root = box ;
    }

    public Parent getRoot() {
        return this.root ;
    }
}
