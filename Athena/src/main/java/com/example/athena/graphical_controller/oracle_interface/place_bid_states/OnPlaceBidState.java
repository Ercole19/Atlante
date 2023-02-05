package com.example.athena.graphical_controller.oracle_interface.place_bid_states;

import com.example.athena.graphical_controller.oracle_interface.OracleBookPageGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.OnPlaceBidView;

public class OnPlaceBidState implements PlaceBidAbstractState {
    public OnPlaceBidState(OracleBookPageGC controller)  {
        ParentSubject.getInstance().setCurrentParent(new OnPlaceBidView(controller).getRoot());
    }

    @Override
    public void goNext(OracleBookPageGC contextStateMachine) {
        throw new UnsupportedOperationException("the state is an ending one");
    }
}
