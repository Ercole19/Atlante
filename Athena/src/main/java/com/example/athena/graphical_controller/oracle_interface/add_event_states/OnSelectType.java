package com.example.athena.graphical_controller.oracle_interface.add_event_states;

import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.SelectTypeView;

public class OnSelectType extends AddEventAbstractState {


    public OnSelectType(OracleAddEventGC controller) {
        ParentSubject.getInstance().setCurrentParent(new SelectTypeView(controller).getRoot()) ;
    }

    @Override
    public void goNext(OracleAddEventGC contextStateMachine) {
        contextStateMachine.setState(new OnWantDescription(contextStateMachine)) ;
    }
}
