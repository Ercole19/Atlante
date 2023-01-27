package com.example.athena.engineering_classes;

import javafx.scene.control.SpinnerValueFactory;

public class HourValueFactory {

    private HourValueFactory () {}

    public static SpinnerValueFactory<Integer> createHourValueFactory(int value) {
        SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23) ;
        hourValueFactory.setWrapAround(true) ;
        if (value >= 0 && value < 24) hourValueFactory.setValue(value) ;
        else hourValueFactory.setValue(0) ;
        return hourValueFactory ;
    }
}
