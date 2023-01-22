package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.normal.EventBean;
import com.example.athena.graphical_controller.EventsViewGC;
import com.example.athena.graphical_controller.oracle_interface.parsers.ShowEventParser;
import com.example.athena.view.EventsView;
import com.example.athena.view.oracle_view.LabelView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OracleEventsViewGC extends EventsViewGC {

    public OracleEventsViewGC(double containerWidth, double containerHeight) {
        super.view = new EventsView(containerWidth, containerHeight, this) ;
    }

    @Override
    protected void editButtonBehavior(EventBean eventBean) {
        new OracleEditEventGC(eventBean) ;
    }

    @Override
    protected void refreshScreen(LocalDate date) {
        ShowEventParser parser = new ShowEventParser();
        List<String> list = new ArrayList<>();
        list.add(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        parser.showEventParse(list);
    }
}
