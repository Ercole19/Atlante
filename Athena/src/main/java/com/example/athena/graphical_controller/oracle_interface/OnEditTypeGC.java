package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.view.oracle_view.OnEditTypeView;

public class OnEditTypeGC {

    private final OracleEditEventGC controller ;

    private final OnEditTypeView view ;

    public OnEditTypeGC(OracleEditEventGC controller) {
        this.controller = controller ;
        this.view = new OnEditTypeView() ;
        setUpInterface() ;
        ParentSubject.getInstance().setCurrentParent(view.getRoot());
    }

    private void setUpInterface() {
        this.view.getChoiceBox().getItems().addAll("Study session", "Lecture time", "Other");
        String type = controller.getType() ;
        type = type.charAt(0) + type.substring(1).toLowerCase().replace("_", " ") ;
        this.view.getChoiceBox().setValue(type) ;

        this.view.getDoneButton().setOnAction(event -> onSaveClick()) ;
    }

    public void onSaveClick() {
        this.controller.setType(this.view.getChoiceBox().getValue());
        this.controller.advance() ;
    }
}
