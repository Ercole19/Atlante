package com.example.athena.graphical_controller.oracle_interface.sell_book_states;

import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;
import com.example.athena.graphical_controller.oracle_interface.OracleSellBookGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

public class OnSellFinalizationState implements SellBookAbstractState {

    public OnSellFinalizationState(OracleSellBookGC controller) {
        try {
            controller.putBookOnSale() ;
        } catch (BookException e) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Process failed. Details:" + e.getMessage()));
            return;
        } catch (ISBNException e) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("ISBN format is incorrect or ISBN doesn't exist"));
            return;
        }
        ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Book successfully put on sale"));
    }

    @Override
    public void goNext(OracleSellBookGC contextStateMachine) {
        throw new UnsupportedOperationException("The state is an ending one") ;
    }
}
