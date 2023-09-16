package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.view.oracle_view.InsertDescriptionView;

public class OnInsertDescriptionGC {

    private final OracleAddEventGC controller ;

    private final InsertDescriptionView view ;

    public OnInsertDescriptionGC(OracleAddEventGC controller) {
        this.controller = controller ;
        this.view = new InsertDescriptionView() ;
        setUpInterface() ;
        ParentSubject.getInstance().setCurrentParent(this.view.getRoot());
    }

    private void setUpInterface() {
        this.view.getConfirm().setOnAction(event -> onConfirmClick());
    }

    public void onConfirmClick() {
        this.controller.setEventDescription(this.view.getDesc().getText()) ;
        this.controller.advance();
    }
}
