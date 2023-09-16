package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.view.oracle_view.WantToView;

public class EventYesOrNoController implements OnYesOrNoController {

    private final OracleAddEventGC controller ;

    private final WantToView view ;

    public EventYesOrNoController(String message, OracleAddEventGC controller) {
        this.controller = controller ;
        this.view = new WantToView(message) ;
        setUpInterface() ;
        ParentSubject.getInstance().setCurrentParent(view.getRoot());
    }

    private void setUpInterface() {
        this.view.getYes().setOnAction(event -> onYes());
        this.view.getNo().setOnAction(event -> onNo()) ;
    }


    @Override
    public void onYes() {
        this.controller.setDecision(true);
        this.controller.advance();
    }

    @Override
    public void onNo() {
        this.controller.setDecision(false) ;
        this.controller.advance();
    }
}
