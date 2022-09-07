package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.engineering_classes.abstract_factory.SearchResultProduct;
import com.example.athena.entities.CalendarSubject;
import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.beans.EventBean;
import com.example.athena.use_case_controllers.ManageEventUCC;
import com.example.athena.view.EventsView;
import com.example.athena.view.LabelBuilder;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class EventsViewGC {

    private EventsView view;
    private List<EventBean> searchResults;

    public EventsViewGC(EventsView view) {
        this.view = view;
    }

    public int getResultSize(LocalDate date) {
        try {
            this.searchResults = CalendarSubject.getInstance().getEntity(YearMonth.of(date.getYear(), date.getMonth())).getEvents(date) ;
        } catch (EventException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
        return this.searchResults.size() ;
    }

    public void setValues(SearchResultProduct searchResultProduct) {
        int i = 0;
        searchResultProduct.setLegend(0, LabelBuilder.buildLabel("Event name"));
        searchResultProduct.setLegend(1, LabelBuilder.buildLabel("Start"));
        searchResultProduct.setLegend(2, LabelBuilder.buildLabel("End"));
        searchResultProduct.setLegend(3, LabelBuilder.buildLabel("Event type"));

        try {
            for (EventBean eventBean : searchResults) {

                searchResultProduct.setEntry(i, 0, LabelBuilder.buildLabel(eventBean.getName()));
                searchResultProduct.setEntry(i, 1, LabelBuilder.buildLabel(eventBean.getStart().toString()));
                searchResultProduct.setEntry(i, 2, LabelBuilder.buildLabel(eventBean.getEnd().toString()));
                searchResultProduct.setEntry(i, 3, LabelBuilder.buildLabel(eventBean.getType().charAt(0) + eventBean.getType().substring(1).toLowerCase().replace("_", " ")));

                Button description = new Button("Description");
                description.setOnAction(event -> {
                    SceneSwitcher switcher = new SceneSwitcher();
                    ArrayList<Object> params = new ArrayList<>() ;
                    params.add(eventBean.getDescription());
                    switcher.popup("EventDescription.fxml", "Event description", params);
                });

                searchResultProduct.setEntry(i, 4, description);

                Button delete = new Button("Delete");
                delete.setOnAction(event -> {
                    try {
                        ManageEventUCC manageEventUCC = new ManageEventUCC();
                        manageEventUCC.deleteEvent(eventBean);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
                        refreshScreen(eventBean.getDate(), stage);
                    } catch (EventException | SendEmailException exc) {
                        SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, exc.getMessage());
                        alert.showAndWait();
                    }
                });
                searchResultProduct.setEntry(i, 5, delete);

                Button edit = new Button("edit");
                edit.setOnAction(event -> {
                    SceneSwitcher switcher = new SceneSwitcher();
                    ArrayList<Object> params = new ArrayList<>();
                    params.add(eventBean);
                    switcher.popup("AddEventScreen.fxml", "Edit your event", params);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
                    refreshScreen(eventBean.getDate(), stage) ;
                });
                searchResultProduct.setEntry(i, 6, edit);
                i++ ;
            }
        }
        catch (IndexOutOfBoundsException exc) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, exc.getMessage());
            alert.showAndWait();
        }
    }

    private void refreshScreen(LocalDate date, Stage stage) {
        SceneSwitcher switcher = new SceneSwitcher() ;
        ArrayList<Object> params = new ArrayList<>() ;
        params.add(date);
        switcher.switcher(stage, "eventPage.fxml", params);
    }
}
