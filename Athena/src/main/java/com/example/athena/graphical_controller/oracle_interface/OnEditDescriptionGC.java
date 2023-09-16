package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.view.oracle_view.OnEditDescriptionView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class OnEditDescriptionGC {

    private final OracleEditEventGC controller ;

    private final OnEditDescriptionView view ;

    public OnEditDescriptionGC(OracleEditEventGC controller) {
        this.controller = controller ;
        this.view = new OnEditDescriptionView() ;
        setUpInterface() ;
        ParentSubject.getInstance().setCurrentParent(view.getRoot());
    }

    private void setUpInterface() {
        this.view.getDescriptionArea().setText(controller.getDescription()) ;
        this.view.getDoneButton().setOnAction(event -> onSaveClick()) ;
    }

    public void onSaveClick() {
        try {
            String desc = this.view.getDescriptionArea().getText() ;
            this.controller.setDescription(desc) ;
            this.controller.advance() ;
        } catch (EventException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK) ;
            alert.showAndWait() ;
        }
    }
}
