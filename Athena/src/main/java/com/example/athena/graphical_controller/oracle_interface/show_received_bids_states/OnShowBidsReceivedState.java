package com.example.athena.graphical_controller.oracle_interface.show_received_bids_states;


import com.example.athena.graphical_controller.oracle_interface.OracleShowReceivedBidsGC;

public class OnShowBidsReceivedState implements ShowReceivedAbstractState{
    public OnShowBidsReceivedState(OracleShowReceivedBidsGC controller) {
        controller.showBids(controller.getSelectedBean());
    }

    @Override
    public void goNext(OracleShowReceivedBidsGC contextStateMachine) {
        throw new UnsupportedOperationException("the state is an ending one");
    }
}
