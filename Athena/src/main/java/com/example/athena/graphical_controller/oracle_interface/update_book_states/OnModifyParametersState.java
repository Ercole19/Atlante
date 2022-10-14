package com.example.athena.graphical_controller.oracle_interface.update_book_states;

import com.example.athena.graphical_controller.oracle_interface.OracleUpdateBookGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.ModifyParametersView;

public class OnModifyParametersState implements UpdateBookAbstractState {
    public OnModifyParametersState(OracleUpdateBookGC controller) {
        ParentSubject.getInstance().setCurrentParent(new ModifyParametersView(controller).getRoot());
    }

    @Override
    public void goNext(OracleUpdateBookGC contextStateMachine) {
        contextStateMachine.setState(new OnUpdateBookFinalization(contextStateMachine));
    }
}
