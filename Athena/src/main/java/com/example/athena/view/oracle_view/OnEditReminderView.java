package com.example.athena.view.oracle_view;

import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OnEditReminderView {

    private final Parent root ;

    private final Spinner<Integer> hourSpinner ;

    private final Spinner<Integer> minuteSpinner ;

    private final Button done ;

    public OnEditReminderView() {

        VBox finalRoot = new VBox() ;

        Label mainText = LabelBuilder.buildLabel("Enter new reminder") ;

        HBox spinnerBox = new HBox() ;

        hourSpinner = new Spinner<>() ;
        Label hb = LabelBuilder.buildLabel("hours and") ;
        minuteSpinner = new Spinner<>() ;
        Label mb = LabelBuilder.buildLabel("minutes before") ;

        done = new Button("Done") ;

        spinnerBox.getChildren().addAll(hourSpinner, hb, minuteSpinner, mb) ;

        finalRoot.getChildren().addAll(mainText, spinnerBox, done) ;

        this.root = finalRoot ;
    }

    public Parent getRoot() {
        return root;
    }

    public Spinner<Integer> getHourSpinner() {
        return hourSpinner;
    }

    public Spinner<Integer> getMinuteSpinner() {
        return minuteSpinner;
    }

    public Button getDone() {
        return done;
    }
}
