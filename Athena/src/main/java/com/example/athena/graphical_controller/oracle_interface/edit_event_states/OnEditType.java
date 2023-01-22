package com.example.athena.graphical_controller.oracle_interface.edit_event_states;

import com.example.athena.graphical_controller.oracle_interface.OnEditTypeGC;
import com.example.athena.graphical_controller.oracle_interface.OracleEditEventGC;

public class OnEditType implements EditEventAbstractState {

    public OnEditType(OracleEditEventGC controller) {
        new OnEditTypeGC(controller) ;
    }

    @Override
    public void goNext(EditEventSM contextStateMachine) {
        contextStateMachine.setState(new OnSelectWhatToEdit(contextStateMachine.getController()));
    }
}
