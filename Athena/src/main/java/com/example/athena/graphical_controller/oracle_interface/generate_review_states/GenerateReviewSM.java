package com.example.athena.graphical_controller.oracle_interface.generate_review_states;

import com.example.athena.graphical_controller.oracle_interface.OracleGenerateReviewGC;

public class GenerateReviewSM {

    private final OracleGenerateReviewGC controller ;

    private GenerateReviewAbstractState current ;

    public GenerateReviewSM(OracleGenerateReviewGC controller, GenerateReviewAbstractState startState) {
        this.controller = controller ;
        this.current = startState ;
    }

    public void setState(GenerateReviewAbstractState state) {
        this.current = state;
    }

    public OracleGenerateReviewGC getController() {
        return controller;
    }

    public void goNext() {
        current.goNext(this) ;
    }
}
