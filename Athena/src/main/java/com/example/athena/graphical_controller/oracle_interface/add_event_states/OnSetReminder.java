package com.example.athena.graphical_controller.oracle_interface.add_event_states;

import com.example.athena.graphical_controller.oracle_interface.OnSetReminderGC;
import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;

public class OnSetReminder implements AddEventAbstractState{

    public OnSetReminder(OracleAddEventGC controller) {
        new OnSetReminderGC(controller) ;
    }

    @Override
    public void goNext(AddEventSM contextStateMachine) {
        contextStateMachine.setState(new OnFinalization(contextStateMachine.getController())) ;
    }
}
