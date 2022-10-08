package com.example.athena.graphical_controller.oracle_interface.generate_review_states;

import com.example.athena.graphical_controller.oracle_interface.OracleGenerateReviewGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.ChooseSubjectView;

public class OnChooseSubjectState implements GenerateReviewAbstractState{

    public OnChooseSubjectState(OracleGenerateReviewGC controller) {
        ParentSubject.getInstance().setCurrentParent(new ChooseSubjectView(controller).getRoot()) ;
    }
   
    @Override
    public void goNext(OracleGenerateReviewGC contextStateMachine) {
        contextStateMachine.setState(new OnGenerateReviewFinalizationState(contextStateMachine)) ;
    }
}
