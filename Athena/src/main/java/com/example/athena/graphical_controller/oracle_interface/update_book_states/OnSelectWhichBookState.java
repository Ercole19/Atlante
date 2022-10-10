package com.example.athena.graphical_controller.oracle_interface.update_book_states;

import com.example.athena.graphical_controller.oracle_interface.OracleUpdateBookGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.SelectWhichBookView;

public class OnSelectWhichBookState implements UpdateBookAbstractState {
    public OnSelectWhichBookState(OracleUpdateBookGC controller) {
        ParentSubject.getInstance().setCurrentParent(new SelectWhichBookView(controller).getRoot());
    }


    @Override
    public void goNext(OracleUpdateBookGC contextStateMachine) {
        contextStateMachine.setState(new OnModifyParametersState(contextStateMachine));
    }
}
