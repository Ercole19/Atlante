package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.engineering_classes.HourValueFactory;
import com.example.athena.engineering_classes.MinuteValueFactory;
import com.example.athena.exceptions.EventException;
import com.example.athena.view.oracle_view.OnEditReminderView;

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
            LocalDateTime reminderDate = this.controller.getDateOfReminder() ;
            LocalDateTime eventStart = LocalDateTime.of(this.controller.getEventDay(), this.controller.getEventStart()) ;
            long distance = reminderDate.until(eventStart, ChronoUnit.MINUTES) ;
            hoursBefore = (int) distance / 60 ;
            minutesBefore = (int) distance % 60 ;

        } catch (EventException e) {
            hoursBefore = 0 ;
            minutesBefore = 0 ;
        }

        this.view.getHourSpinner().setValueFactory(HourValueFactory.createHourValueFactory(hoursBefore)) ;
        this.view.getMinuteSpinner().setValueFactory(MinuteValueFactory.createMinuteValueFactory(minutesBefore)) ;
        this.view.getDone().setOnAction(event -> onSaveClick());
    }

    public void onSaveClick() {
        this.controller.setReminder(this.view.getHourSpinner().getValue(), this.view.getMinuteSpinner().getValue()) ;
        this.controller.advance() ;
    }
}
