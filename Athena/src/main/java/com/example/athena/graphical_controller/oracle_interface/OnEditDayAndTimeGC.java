package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.graphical_controller.oracle_interface.edit_event_states.OnEditDayAndTime;
import com.example.athena.view.oracle_view.OnEditDayAndTimeView;
import javafx.scene.control.SpinnerValueFactory;

import java.time.LocalTime;

public class OnEditDayAndTimeGC {

    private final OracleEditEventGC controller ;
    private final OnEditDayAndTimeView view ;

    public OnEditDayAndTimeGC(OracleEditEventGC controller) {
        this.controller = controller ;
        this.view = new OnEditDayAndTimeView() ;
        setUpInterface() ;
        ParentSubject.getInstance().setCurrentParent(view.getRoot());
    }

    private void setUpInterface() {
        LocalTime startTime = controller.getEventStart() ;
        LocalTime endTime = controller.getEnd() ;

        this.view.getFirstHourSpinner().setValueFactory(createHourValueFactory(startTime.getHour())) ;
        this.view.getFirstMinuteSpinner().setValueFactory(createMinuteValueFactory(startTime.getMinute())) ;
        this.view.getSecondHourSpinner().setValueFactory(createHourValueFactory(endTime.getHour())) ;
        this.view.getSecondMinuteSpinner().setValueFactory(createMinuteValueFactory(endTime.getMinute())) ;

        this.view.getDone().setOnAction(event -> onSaveClick());
    }

    public void onSaveClick() {
        LocalTime startTime = LocalTime.of(this.view.getFirstHourSpinner().getValue(), this.view.getFirstMinuteSpinner().getValue()) ;
        LocalTime endTime = LocalTime.of(this.view.getSecondHourSpinner().getValue(), this.view.getSecondMinuteSpinner().getValue()) ;
        this.controller.setStart(startTime) ;
        this.controller.setEnd(endTime) ;
        this.controller.advance() ;
    }

    private SpinnerValueFactory<Integer> createHourValueFactory(int value) {
        SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23) ;
        hourValueFactory.setWrapAround(true) ;
        if (value >= 0 && value < 24) hourValueFactory.setValue(value) ;
        else hourValueFactory.setValue(0) ;
        return hourValueFactory ;
    }

    private SpinnerValueFactory<Integer> createMinuteValueFactory(int value) {
        SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59) ;
        minuteValueFactory.setWrapAround(true) ;
        if (value >= 0 && value < 60) minuteValueFactory.setValue(value) ;
        else minuteValueFactory.setValue(0) ;
        return minuteValueFactory ;
    }
}
