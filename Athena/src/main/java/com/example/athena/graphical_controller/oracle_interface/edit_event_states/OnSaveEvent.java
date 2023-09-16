package com.example.athena.graphical_controller.oracle_interface.edit_event_states;

import com.example.athena.graphical_controller.oracle_interface.OracleEditEventGC;
import com.example.athena.graphical_controller.oracle_interface.parsers.ShowCalendarParser;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OnSaveEvent implements EditEventAbstractState {

    public OnSaveEvent(OracleEditEventGC controller) {
        controller.updateEvent();
        ArrayList<String> command = new ArrayList<>() ;
        command.add(controller.getEventDay().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))) ;
        new ShowCalendarParser().showEventParse(command) ;
    }
    @Override
    public void goNext(EditEventSM contextStateMachine) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("End State") ;
    }
}
