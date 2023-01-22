package com.example.athena.graphical_controller.oracle_interface.edit_event_states;

import com.example.athena.graphical_controller.oracle_interface.OnSelectWhatToEditGC;
import com.example.athena.graphical_controller.oracle_interface.OracleEditEventGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

public class OnSelectWhatToEdit implements EditEventAbstractState{

    private EditStatesEnum nextState ;

    public OnSelectWhatToEdit(OracleEditEventGC controller) {
        new OnSelectWhatToEditGC(controller, this) ;
    }

    public void setNextState(EditStatesEnum nextState) {
        this.nextState = nextState ;
    }

    @Override
    public void goNext(EditEventSM contextStateMachine) {
        switch (nextState) {
            case EDIT_DESC:
                contextStateMachine.setState(new OnEditDescription(contextStateMachine.getController())) ;
                break ;
            case EDIT_TIME:
                contextStateMachine.setState(new OnEditDayAndTime(contextStateMachine.getController())) ;
                break;
            case EDIT_TYPE:
                contextStateMachine.setState(new OnEditType(contextStateMachine.getController())) ;
                break;
            case EDIT_REMINDER:
                contextStateMachine.setState(new OnEditReminder(contextStateMachine.getController())) ;
                break;
            case SAVE_EVENT:
                contextStateMachine.setState(new OnSaveEvent(contextStateMachine.getController())) ;
                break;
        }
    }
}
