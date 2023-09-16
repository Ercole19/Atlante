package com.example.athena.graphical_controller.oracle_interface.add_event_states;

import com.example.athena.graphical_controller.oracle_interface.EventYesOrNoController;
import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;

public class OnWantReminder implements AddEventAbstractState{

    public OnWantReminder(OracleAddEventGC controller) {
        new EventYesOrNoController("Do you want to add a reminder ?", controller) ;
    }

    @Override
    public void goNext(AddEventSM contextStateMachine) {
        if(contextStateMachine.getController().getDecision()) {
            contextStateMachine.setState(new OnSetReminder(contextStateMachine.getController()));
        } else {
            contextStateMachine.setState(new OnFinalization(contextStateMachine.getController())) ;
        }
    }
}
