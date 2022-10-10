package com.example.athena.graphical_controller.oracle_interface.update_book_states;

import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.graphical_controller.oracle_interface.OracleUpdateBookGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

public class OnUpdateBookFinalization implements UpdateBookAbstractState{

    public OnUpdateBookFinalization(OracleUpdateBookGC contextStateMachine) {
        LabelView view = new LabelView();
        try {
            contextStateMachine.executeBookUpdate();
        } catch (ISBNException | BookException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in updating book, details follow: " + e.getMessage()));
        }
        ParentSubject.getInstance().setCurrentParent(view.prepareParent("Book updated correctly"));
    }

    @Override
    public void goNext(OracleUpdateBookGC contextStateMachine){
        throw new UnsupportedOperationException("the state is an ending one");
    }
}
