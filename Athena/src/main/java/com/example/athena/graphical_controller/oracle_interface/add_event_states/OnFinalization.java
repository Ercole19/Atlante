package com.example.athena.graphical_controller.oracle_interface.add_event_states;

import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;
import com.example.athena.view.oracle_view.SetReminderView;

public class OnFinalization extends AddEventAbstractState {

    public OnFinalization(OracleAddEventGC controller) {
        try {
            controller.executeAddition() ;
        } catch (UseCaseException e) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Adding failed"));
            return;
        }
        ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Event added"));
    }

    @Override
    public void goNext(OracleAddEventGC contextStateMachine) {

    }
}
