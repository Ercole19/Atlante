package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.engineering_classes.HourValueFactory;
import com.example.athena.engineering_classes.MinuteValueFactory;
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

        this.view.getFirstHourSpinner().setValueFactory(HourValueFactory.createHourValueFactory(startTime.getHour())) ;
        this.view.getFirstMinuteSpinner().setValueFactory(MinuteValueFactory.createMinuteValueFactory(startTime.getMinute())) ;
        this.view.getSecondHourSpinner().setValueFactory(HourValueFactory.createHourValueFactory(endTime.getHour())) ;
        this.view.getSecondMinuteSpinner().setValueFactory(MinuteValueFactory.createMinuteValueFactory(endTime.getMinute())) ;

        this.view.getDone().setOnAction(event -> onSaveClick());
    }

    public void onSaveClick() {
        LocalTime startTime = LocalTime.of(this.view.getFirstHourSpinner().getValue(), this.view.getFirstMinuteSpinner().getValue()) ;
        LocalTime endTime = LocalTime.of(this.view.getSecondHourSpinner().getValue(), this.view.getSecondMinuteSpinner().getValue()) ;
        this.controller.setStart(startTime) ;
        this.controller.setEnd(endTime) ;
        this.controller.advance() ;
    }
}
