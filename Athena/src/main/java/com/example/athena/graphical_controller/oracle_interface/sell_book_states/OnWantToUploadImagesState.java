package com.example.athena.graphical_controller.oracle_interface.sell_book_states;

import com.example.athena.graphical_controller.oracle_interface.BookYesOrNoController;
import com.example.athena.graphical_controller.oracle_interface.OracleSellBookGC;

public class OnWantToUploadImagesState implements SellBookAbstractState {

    public OnWantToUploadImagesState(OracleSellBookGC controller) {
        new BookYesOrNoController("Do you want to add images ?", controller) ;
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
