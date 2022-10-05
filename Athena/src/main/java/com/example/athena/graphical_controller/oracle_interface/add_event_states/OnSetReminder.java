package com.example.athena.graphical_controller.oracle_interface.add_event_states;

import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.SetReminderView;

public class OnSetReminder implements AddEventAbstractState{

    public OnSetReminder(OracleAddEventGC controller) {
        ParentSubject.getInstance().setCurrentParent(new SetReminderView(controller).getRoot());
    }

    @Override
    public void goNext(OracleAddEventGC contextStateMachine) {
        contextStateMachine.setState(new OnFinalization(contextStateMachine)) ;
    }
}
