package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.entities.ActivityTypesEnum;
import com.example.athena.view.oracle_view.OnSelectTypeView;
import javafx.scene.control.ChoiceBox;

public class OnSelectTypeGC {

    private final OracleAddEventGC controller ;

    private final OnSelectTypeView view ;

    public OnSelectTypeGC(OracleAddEventGC controller) {
        this.controller = controller ;
        this.view = new OnSelectTypeView() ;
        setUpInterface() ;
        ParentSubject.getInstance().setCurrentParent(this.view.getRoot());
    }

    private void setUpInterface() {
        ChoiceBox<String> box = this.view.getChoiceBox() ;

        for (ActivityTypesEnum elem : ActivityTypesEnum.values()) {
            String text = elem.toString().charAt(0) + elem.toString().substring(1).toLowerCase().replace('_', ' ') ;
            box.getItems().add(text);
        }

        this.view.getConfirm().setOnAction(event -> onSaveClick()) ;
    }

    public void onSaveClick() {
        this.controller.setEventType(this.view.getChoiceBox().getValue().toUpperCase().replace(" ", "_")) ;
        this.controller.advance();
    }
}
