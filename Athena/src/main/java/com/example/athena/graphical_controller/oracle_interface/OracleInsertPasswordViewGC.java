package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.view.oracle_view.InsertPasswordView;

public class OracleInsertPasswordViewGC {

    private final OracleLoginGC mainController ;
    private final InsertPasswordView view ;

    public OracleInsertPasswordViewGC(OracleLoginGC controller) {
        this.mainController = controller ;
        this.view = new InsertPasswordView() ;
        this.view.getConfirm().setOnAction(event -> onPress());
        this.view.getNameLabel().setText("Enter password for " + mainController.getUsername() + ":") ;
        ParentSubject.getInstance().setCurrentParent(this.view.getRoot());
    }

    public void onPress() {
        mainController.setPassword(this.view.getPasswordField().getText()) ;
        mainController.advance();
    }
}
