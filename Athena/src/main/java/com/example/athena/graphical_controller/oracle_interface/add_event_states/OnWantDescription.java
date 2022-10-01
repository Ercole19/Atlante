package com.example.athena.graphical_controller.oracle_interface.add_event_states;

import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.WantToView;

public class OnWantDescription extends AddEventAbstractState{


    public OnWantDescription(OracleAddEventGC controller) {
        ParentSubject.getInstance().setCurrentParent(new WantToView("Want to set description?", controller).getRoot());
    }

    @Override
    public void goNext(OracleAddEventGC contextStateMachine) {
        if(contextStateMachine.getDecision()) {
            contextStateMachine.setState(new OnInsertDescription(contextStateMachine));
        } else {
            contextStateMachine.setState(new OnWantReminder(contextStateMachine)) ;
        }
    }


}