package com.example.athena.graphical_controller.oracle_interface.sell_book_states;

import com.example.athena.graphical_controller.oracle_interface.OracleSellBookGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.SelectNegotiabilityView;

public class OnSelectNegotiabilityState implements SellBookAbstractState {

    public OnSelectNegotiabilityState(OracleSellBookGC controller) {
        ParentSubject.getInstance().setCurrentParent(new SelectNegotiabilityView(controller).getRoot());
    }

    @Override
    public void goNext(OracleSellBookGC controller) {
        controller.setState(new OnWantToUploadImagesState(controller)) ;
    }
}
