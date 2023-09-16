package com.example.athena.graphical_controller.oracle_interface.show_received_bids_states;


import com.example.athena.graphical_controller.oracle_interface.OracleShowReceivedBidsGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.ShowBidsOfWhichBookView;

public class OnSelectWhichBookToShowState implements ShowReceivedAbstractState {
    public OnSelectWhichBookToShowState(OracleShowReceivedBidsGC oracleShowReceivedBidsGC) {
        ParentSubject.getInstance().setCurrentParent(new ShowBidsOfWhichBookView(oracleShowReceivedBidsGC).getRoot());
    }

    @Override
    public void goNext(OracleShowReceivedBidsGC contextStateMachine) {
        contextStateMachine.setState(new OnShowBidsReceivedState(contextStateMachine));
    }
}
