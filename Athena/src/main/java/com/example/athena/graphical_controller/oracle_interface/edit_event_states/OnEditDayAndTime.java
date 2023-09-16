package com.example.athena.graphical_controller.oracle_interface.edit_event_states;

import com.example.athena.graphical_controller.oracle_interface.OnEditDayAndTimeGC;
import com.example.athena.graphical_controller.oracle_interface.OracleEditEventGC;

public class OnEditDayAndTime implements EditEventAbstractState {

    public OnEditDayAndTime(OracleEditEventGC controller) {
        new OnEditDayAndTimeGC(controller) ;
    }

    @Override
    public void goNext(EditEventSM contextStateMachine) {
        contextStateMachine.setState(new OnSelectWhatToEdit(contextStateMachine.getController()));
    }
}
