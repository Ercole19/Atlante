package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.view.oracle_view.WantToView;

public class BookYesOrNoController implements OnYesOrNoController {

    private final OracleSellBookGC controller ;

    private final WantToView view ;

    public BookYesOrNoController(String message, OracleSellBookGC controller) {
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
        this.controller.onYes();
    }

    @Override
    public void onNo() {
        this.controller.onNo() ;
    }
}
