package com.example.athena.graphical_controller.oracle_interface.edit_event_states;

import com.example.athena.graphical_controller.oracle_interface.OnEditReminderGC;
import com.example.athena.graphical_controller.oracle_interface.OracleEditEventGC;

public class OnEditReminder implements EditEventAbstractState {

    public OnEditReminder(OracleEditEventGC controller) {
        new OnEditReminderGC(controller) ;
    }
    @Override
    public void goNext(EditEventSM contextStateMachine) {
        contextStateMachine.setState(new OnSelectWhatToEdit(contextStateMachine.getController())) ;
    }
}
