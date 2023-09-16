package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.graphical_controller.oracle_interface.edit_event_states.EditStatesEnum;
import com.example.athena.graphical_controller.oracle_interface.edit_event_states.OnSelectWhatToEdit;
import com.example.athena.view.oracle_view.OnSelectWhatToEditView;

public class OnSelectWhatToEditGC {

    private final OracleEditEventGC controller ;
    private final OnSelectWhatToEdit state ;

    private final OnSelectWhatToEditView view ;

    public OnSelectWhatToEditGC(OracleEditEventGC controller, OnSelectWhatToEdit state) {
        this.controller = controller ;
        this.state = state ;
        this.view = new OnSelectWhatToEditView() ;
        setUpInterface() ;
        ParentSubject.getInstance().setCurrentParent(view.getRoot()) ;

    }

    private void setUpInterface() {
        view.getEditDesc().setOnAction(event -> setChoice(EditStatesEnum.EDIT_DESC)) ;
        view.getEditTime().setOnAction(event -> setChoice(EditStatesEnum.EDIT_TIME));
        view.getEditReminder().setOnAction(event -> setChoice(EditStatesEnum.EDIT_REMINDER));
        view.getEditType().setOnAction(event -> setChoice(EditStatesEnum.EDIT_TYPE));
        view.getSaveChanges().setOnAction(event -> setChoice(EditStatesEnum.SAVE_EVENT)) ;

    }

    public void setChoice(EditStatesEnum choice) {
        this.state.setNextState(choice) ;
        this.controller.advance() ;
    }
}
