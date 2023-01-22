package com.example.athena.graphical_controller.oracle_interface.edit_event_states;

import com.example.athena.graphical_controller.oracle_interface.OnEditDescriptionGC;
import com.example.athena.graphical_controller.oracle_interface.OracleEditEventGC;

public class OnEditDescription implements EditEventAbstractState {

    public OnEditDescription(OracleEditEventGC controller) {
        new OnEditDescriptionGC(controller) ;
    }
    @Override
    public void goNext(EditEventSM contextStateMachine) {
        contextStateMachine.setState(new OnSelectWhatToEdit(contextStateMachine.getController())) ;
    }
}
