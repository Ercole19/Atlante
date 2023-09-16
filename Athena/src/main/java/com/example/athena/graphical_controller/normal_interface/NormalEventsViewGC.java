package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.EventBean;
import com.example.athena.graphical_controller.EventsViewGC;
import com.example.athena.view.EventsView;

import java.time.LocalDate;
import java.util.ArrayList;

public class NormalEventsViewGC extends EventsViewGC {

    public NormalEventsViewGC(double containerWidth, double containerHeight) {
        super.view = new EventsView(containerWidth, containerHeight, this) ;
    }

    @Override
    protected void editButtonBehavior(EventBean eventBean) {
        ArrayList<Object> params = new ArrayList<>();
        params.add(eventBean);
        SceneSwitcher.getInstance().popup("AddEventScreen.fxml", "Edit your event", params);
        refreshScreen(eventBean.getDate()) ;
    }

    @Override
    protected void refreshScreen(LocalDate date) {
        ArrayList<Object> params = new ArrayList<>() ;
        params.add(date);
        SceneSwitcher.getInstance().switcher("eventPage.fxml", params);
    }
}
