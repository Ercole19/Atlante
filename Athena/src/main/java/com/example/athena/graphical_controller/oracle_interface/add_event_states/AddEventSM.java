package com.example.athena.graphical_controller.oracle_interface.add_event_states;

import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;

public class AddEventSM {

    private final OracleAddEventGC controller ;

    private AddEventAbstractState current ;

    public AddEventSM(OracleAddEventGC controller, AddEventAbstractState startState) {
        this.controller = controller ;
        this.current = startState ;
    }

    public void setState(AddEventAbstractState state) {
        this.current = state;
    }

    public OracleAddEventGC getController() {
        return controller;
    }

    public void goNext() {
        current.goNext(this) ;
    }
}
