package com.example.athena.graphical_controller.oracle_interface.generate_review_states;

import com.example.athena.graphical_controller.oracle_interface.OnChooseSubjectGC;
import com.example.athena.graphical_controller.oracle_interface.OracleGenerateReviewGC;

public class OnChooseSubjectState implements GenerateReviewAbstractState{

    public OnChooseSubjectState(OracleGenerateReviewGC controller) {
        new OnChooseSubjectGC(controller) ;
    }
   
    @Override
    public void goNext(GenerateReviewSM contextStateMachine) {
        contextStateMachine.setState(new OnGenerateReviewFinalizationState(contextStateMachine.getController())) ;
    }
}
