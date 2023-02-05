package com.example.athena.graphical_controller.oracle_interface.place_bid_states;

import com.example.athena.graphical_controller.oracle_interface.OracleBookPageGC;

public class OnBookPageBid implements PlaceBidAbstractState {
    @Override
    public void goNext(OracleBookPageGC contextStateMachine) {
        contextStateMachine.setState(new OnPlaceBidState(contextStateMachine));
    }
}
