package com.example.athena.view.oracle_view;

import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WantToView {

    private Parent root ;

    private final Button yes ;

    private final Button no ;


    public WantToView(String message) {
        VBox box = new VBox() ;
        box.getChildren().add(LabelBuilder.buildLabel(message)) ;
        HBox yesOrNo = new HBox() ;
        box.getChildren().add(yesOrNo) ;

        yes = new Button("Yes") ;
        yesOrNo.getChildren().add(yes) ;

        no = new Button("No") ;
        yesOrNo.getChildren().add(no) ;

        this.root = box ;
    }

    public Parent getRoot() {
        return this.root ;
    }

    public Button getYes() {
        return yes;
    }

    public Button getNo() {
        return no;
    }
}
