package com.example.athena.graphical_controller.oracle_interface.edit_event_states;

import com.example.athena.graphical_controller.oracle_interface.OracleEditEventGC;

public class EditEventSM {

    private final OracleEditEventGC controller ;

    private EditEventAbstractState current ;

    public EditEventSM(OracleEditEventGC controller, EditEventAbstractState startState) {
        this.controller = controller ;
        this.current = startState ;
    }

    public void setState(EditEventAbstractState state) {
        this.current = state;
    }

    public OracleEditEventGC getController() {
        return controller;
    }

    public void goNext() {
        current.goNext(this) ;
    }
}
