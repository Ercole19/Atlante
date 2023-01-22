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
        this.view.getChoiceBox().getItems().addAll("Study Session", "Lecture Time", "Other");
        String type = controller.getType() ;
        String[] tokens = type.split("_") ;
        for (String token : tokens) {
            type = token.charAt(0) + token.substring(1) + " " ;
        }
        type = type.substring(0, type.length() -1) ;
        this.view.getChoiceBox().setValue(type) ;

        this.view.getDoneButton().setOnAction(event -> onSaveClick()) ;
    }

    public void onSaveClick() {
        this.controller.setType(this.view.getChoiceBox().getValue());
        this.controller.advance() ;
    }
}
