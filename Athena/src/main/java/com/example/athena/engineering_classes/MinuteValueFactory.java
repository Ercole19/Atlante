package com.example.athena.engineering_classes;

import javafx.scene.control.SpinnerValueFactory;

public class MinuteValueFactory {

    private MinuteValueFactory() {}

    public static SpinnerValueFactory<Integer> createMinuteValueFactory(int value) {
        SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59) ;
        minuteValueFactory.setWrapAround(true) ;
        if (value >= 0 && value < 60) minuteValueFactory.setValue(value) ;
        else minuteValueFactory.setValue(0) ;
        return minuteValueFactory ;
    }
}
