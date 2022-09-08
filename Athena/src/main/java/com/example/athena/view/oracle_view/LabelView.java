package com.example.athena.view.oracle_view;

import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LabelView {
    public Parent prepareParent(String message) {
        VBox box = new VBox() ;
        box.setPrefSize(300,200);
        Label label = LabelBuilder.buildSmallLabel(message) ;
        box.getChildren().add(label) ;
        return box ;
    }
}
