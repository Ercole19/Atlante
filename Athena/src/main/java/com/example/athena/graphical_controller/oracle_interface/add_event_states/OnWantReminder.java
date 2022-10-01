package com.example.athena.graphical_controller.oracle_interface.add_event_states;

import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.WantToView;

public class OnWantReminder extends AddEventAbstractState{

    public OnWantReminder(OracleAddEventGC controller) {
        ParentSubject.getInstance().setCurrentParent(new WantToView("Do you want to add a reminder ?", controller).getRoot());
    }

    @Override
    public void goNext(OracleAddEventGC contextStateMachine) {
        if(contextStateMachine.getDecision()) {
            contextStateMachine.setState(new OnSetReminder(contextStateMachine));
        } else {
            contextStateMachine.setState(new OnFinalization(contextStateMachine)) ;
        }
    }
}
