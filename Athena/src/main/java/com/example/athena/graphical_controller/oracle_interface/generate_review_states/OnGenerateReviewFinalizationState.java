package com.example.athena.graphical_controller.oracle_interface.generate_review_states;

import com.example.athena.exceptions.TutorReviewException;
import com.example.athena.graphical_controller.oracle_interface.OracleGenerateReviewGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.time.format.DateTimeParseException;

public class OnGenerateReviewFinalizationState implements GenerateReviewAbstractState{

    public OnGenerateReviewFinalizationState(OracleGenerateReviewGC oracleGenerateReviewGC) {
        String code;
        try {
            code = oracleGenerateReviewGC.generateReview();
        }
        catch (DateTimeParseException | TutorReviewException e){
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Process failed. Details:" + e.getMessage()));
            return;
        }
        ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Code generated successfully.\n" + code));
    }

    @Override
    public void goNext(OracleGenerateReviewGC contextStateMachine) {
        throw new UnsupportedOperationException("The state is an ending one");
    }
}
