package com.example.athena.graphical_controller.oracle_interface.sell_book_states;

import com.example.athena.graphical_controller.oracle_interface.OracleSellBookGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.UploadImageView;

public class OnUploadImagesState implements SellBookAbstractState {

    public OnUploadImagesState(OracleSellBookGC controller) {
        ParentSubject.getInstance().setCurrentParent(new UploadImageView(controller).getRoot());
    }

    @Override
    public void goNext(OracleSellBookGC contextStateMachine) {
        contextStateMachine.setState(new OnSellFinalizationState(contextStateMachine));
    }
}
