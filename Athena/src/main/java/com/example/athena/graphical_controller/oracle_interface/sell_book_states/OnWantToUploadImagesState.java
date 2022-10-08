package com.example.athena.graphical_controller.oracle_interface.sell_book_states;

import com.example.athena.graphical_controller.oracle_interface.OracleSellBookGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.WantToView;

public class OnWantToUploadImagesState implements SellBookAbstractState {

    public OnWantToUploadImagesState(OracleSellBookGC controller) {
        ParentSubject.getInstance().setCurrentParent(new WantToView("Do you want to add images ?", controller).getRoot());
    }

    @Override
    public void goNext(OracleSellBookGC contextStateMachine) {
        if(contextStateMachine.getDecision()) {
            contextStateMachine.setState(new OnUploadImagesState(contextStateMachine)) ;
        } else {
            contextStateMachine.setState(new OnSellFinalizationState(contextStateMachine)) ;
        }
    }
}
