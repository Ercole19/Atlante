package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.graphical_controller.oracle_interface.add_event_states.AddEventAbstractState;
import com.example.athena.graphical_controller.oracle_interface.add_event_states.OnSelectType;
import com.example.athena.view.oracle_view.InsertDescriptionView;
import com.example.athena.view.oracle_view.SelectTypeView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class OracleAddEventGC implements OnYesOrNoController{

    private String eventName ;
    private LocalDate eventDay ;
    private LocalTime eventStart ;
    private LocalTime eventEnd ;
    private String eventType ;
    private String eventDescription ;
    private int hoursBefore ;
    private int minutesBefore ;

    private boolean decision ;

    private AddEventAbstractState current ;

    public void addEvent(String name, String date, String start, String end ) {
        this.eventName = name ;
        this.eventDay = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")) ;
        this.eventStart = LocalTime.parse(start) ;
        this.eventEnd = LocalTime.parse(end) ;
        this.current = new OnSelectType(this) ;
    }

    public void obtainEventType(SelectTypeView view) {
        this.eventType = view.getChoiceBox().getValue() ;
        this.goNext();
    }

    public void obtainDescription(InsertDescriptionView view) {
        this.eventDescription = view.getDescription() ;
        this.goNext() ;
    }

    public void setState(AddEventAbstractState nextState) {
        this.current = nextState ;
    }

    public boolean getDecision() {
        return this.decision ;
    }

    public void goNext() {
        this.current.goNext(this) ;
    }

    @Override
    public void onYes() {
        this.decision = true ;
        this.goNext();
    }

    @Override
    public void onNo() {
        this.decision = false ;
        this.goNext();
    }
}
