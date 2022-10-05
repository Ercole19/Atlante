package com.example.athena.graphical_controller.oracle_interface.add_event_states;

import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.InsertDescriptionView;

public class OnInsertDescription implements AddEventAbstractState{

    public OnInsertDescription(OracleAddEventGC controller) {
        ParentSubject.getInstance().setCurrentParent(new InsertDescriptionView(controller).getRoot());
    }

    @Override
    public void goNext(OracleAddEventGC contextStateMachine) {
        contextStateMachine.setState(new OnWantReminder(contextStateMachine));
    }
}
