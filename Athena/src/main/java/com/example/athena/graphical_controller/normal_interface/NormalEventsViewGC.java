package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.engineering_classes.search_result_factory.SearchResultProduct;
import com.example.athena.entities.CalendarSubject;
import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.beans.normal.EventBean;
import com.example.athena.graphical_controller.EventsViewGC;
import com.example.athena.graphical_controller.oracle_interface.parsers.ShowEventParser;
import com.example.athena.use_case_controllers.ManageEventUCC;
import com.example.athena.view.EventsView;
import com.example.athena.view.LabelBuilder;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

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
