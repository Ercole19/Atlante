package com.example.athena.graphical_controller.oracle_interface.add_event_states;

import com.example.athena.graphical_controller.oracle_interface.OnSelectTypeGC;
import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;

public class OnSelectType implements AddEventAbstractState {


    public OnSelectType(OracleAddEventGC controller) {
        new OnSelectTypeGC(controller) ;
    }

    @Override
    public void goNext(AddEventSM contextStateMachine) {
        contextStateMachine.setState(new OnWantDescription(contextStateMachine.getController())) ;
    }
}
