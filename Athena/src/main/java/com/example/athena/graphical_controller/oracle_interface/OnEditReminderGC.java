package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.exceptions.EventException;
import com.example.athena.view.oracle_view.OnEditReminderView;
import javafx.scene.control.SpinnerValueFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class OnEditReminderGC {

    private final OracleEditEventGC controller ;

    private final OnEditReminderView view ;

    public OnEditReminderGC(OracleEditEventGC controller) {
        this.controller = controller ;
        this.view = new OnEditReminderView() ;
        setUpInterface() ;
        ParentSubject.getInstance().setCurrentParent(this.view.getRoot()) ;
    }

    private void setUpInterface() {
        int hoursBefore ;
        int minutesBefore ;

        try {
            LocalDateTime reminderDate = this.controller.getDateOfReminder().toLocalDateTime() ;
            LocalDateTime eventStart = LocalDateTime.of(this.controller.getEventDay(), this.controller.getEventStart()) ;
            long distance = reminderDate.until(eventStart, ChronoUnit.MINUTES) ;
            hoursBefore = (int) distance / 60 ;
            minutesBefore = (int) distance % 60 ;

        } catch (EventException e) {
            hoursBefore = 0 ;
            minutesBefore = 0 ;
        }

        this.view.getHourSpinner().setValueFactory(createHourValueFactory(hoursBefore)) ;
        this.view.getMinuteSpinner().setValueFactory(createMinuteValueFactory(minutesBefore)) ;
        this.view.getDone().setOnAction(event -> onSaveClick());
    }

    public void onSaveClick() {
        this.controller.setReminder(this.view.getHourSpinner().getValue(), this.view.getMinuteSpinner().getValue()) ;
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
