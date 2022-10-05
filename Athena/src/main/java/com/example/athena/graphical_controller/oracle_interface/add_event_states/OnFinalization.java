package com.example.athena.graphical_controller.oracle_interface.add_event_states;

import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;
import com.example.athena.view.oracle_view.SetReminderView;

public class OnFinalization extends AddEventAbstractState {

    public OnFinalization(OracleAddEventGC controller) {
        try {
            controller.saveEvent() ;
        } catch (EventException e) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Adding failed. Details:" + e.getMessage()));
            return;
        } catch (SendEmailException e) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Unable to set reminder; edit the event to set the reminder again"));
            return;
        }
        ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Event added"));
    }

    @Override
    public void goNext(OracleAddEventGC contextStateMachine) {

    }
}
