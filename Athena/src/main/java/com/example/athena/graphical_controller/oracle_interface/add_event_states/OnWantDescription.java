package com.example.athena.graphical_controller.oracle_interface.add_event_states;

import com.example.athena.graphical_controller.oracle_interface.EventYesOrNoController;
import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;

public class OnWantDescription implements AddEventAbstractState{


    public OnWantDescription(OracleAddEventGC controller) {
        new EventYesOrNoController("Want to set description?", controller) ;
    }

    @Override
    public void goNext(AddEventSM contextStateMachine) {
        if(contextStateMachine.getController().getDecision()) {
            contextStateMachine.setState(new OnInsertDescription(contextStateMachine.getController()));
        } else {
            contextStateMachine.setState(new OnWantReminder(contextStateMachine.getController())) ;
        }
    }


}
