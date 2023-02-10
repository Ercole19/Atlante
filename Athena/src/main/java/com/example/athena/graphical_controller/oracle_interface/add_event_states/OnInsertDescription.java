package com.example.athena.graphical_controller.oracle_interface.add_event_states;

import com.example.athena.graphical_controller.oracle_interface.OnInsertDescriptionGC;
import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;

public class OnInsertDescription implements AddEventAbstractState{

    public OnInsertDescription(OracleAddEventGC controller) {
        new OnInsertDescriptionGC(controller) ;
    }

    @Override
    public void goNext(AddEventSM contextStateMachine) {
        contextStateMachine.setState(new OnWantReminder(contextStateMachine.getController()));
    }
}
