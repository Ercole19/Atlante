package com.example.athena.view.oracle_view;

import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OnEditDayAndTimeView {

    private final Parent root ;

    private final Spinner<Integer> firstHourSpinner ;

    private final Spinner<Integer> firstMinuteSpinner ;

    private final Spinner<Integer> secondHourSpinner ;

    private final Spinner<Integer> secondMinuteSpinner ;

    private final Button done ;


    public OnEditDayAndTimeView() {

        VBox finalRoot = new VBox() ;

        Label mainText = LabelBuilder.buildLabel("Edit event's time") ;

        HBox firstSpinnerBox = new HBox() ;

        firstHourSpinner = new Spinner<>() ;
        firstMinuteSpinner = new Spinner<>() ;

        firstSpinnerBox.getChildren().addAll(firstHourSpinner, LabelBuilder.buildLabel(":") , firstMinuteSpinner) ;

        HBox secondSpinnerBox = new HBox() ;

        secondHourSpinner = new Spinner<>() ;
        secondMinuteSpinner = new Spinner<>() ;

        secondSpinnerBox.getChildren().addAll(secondHourSpinner, LabelBuilder.buildLabel(":"), secondMinuteSpinner) ;

        done = new Button("Done") ;

        finalRoot.getChildren().addAll(mainText, firstSpinnerBox, secondSpinnerBox, done) ;

        this.root = finalRoot ;
    }

    public Parent getRoot() {
        return root;
    }

    public Spinner<Integer> getFirstHourSpinner() {
        return firstHourSpinner;
    }

    public Spinner<Integer> getFirstMinuteSpinner() {
        return firstMinuteSpinner;
    }

    public Spinner<Integer> getSecondHourSpinner() {
        return secondHourSpinner;
    }

    public Spinner<Integer> getSecondMinuteSpinner() {
        return secondMinuteSpinner;
    }

    public Button getDone() {
        return done;
    }
}
