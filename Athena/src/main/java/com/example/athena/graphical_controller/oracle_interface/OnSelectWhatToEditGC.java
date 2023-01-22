package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.graphical_controller.oracle_interface.edit_event_states.EditStatesEnum;
import com.example.athena.graphical_controller.oracle_interface.edit_event_states.OnSelectWhatToEdit;
import com.example.athena.view.oracle_view.OnSelectWhatToEditView;

public class OnSelectWhatToEditGC {

    private final OracleEditEventGC controller ;
    private final OnSelectWhatToEdit state ;

    public OnSelectWhatToEditGC(OracleEditEventGC controller, OnSelectWhatToEdit state) {
        this.controller = controller ;
        this.state = state ;
        ParentSubject.getInstance().setCurrentParent(new OnSelectWhatToEditView(this).getRoot()) ;

    }

    public void setChoice(EditStatesEnum choice) {
        this.state.setNextState(choice) ;
        this.controller.advance() ;
    }
}
