package com.example.athena.graphical_controller;

import com.example.athena.engineering_classes.abstract_factory.SearchResultProduct;
import com.example.athena.entities.CalendarSubject;
import com.example.athena.entities.EventDao;
import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.ManageEventUCC;
import com.example.athena.view.EventsView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javafx.scene.layout.GridPane.getRowIndex;

public class EventsViewGC {

    private EventsView view;
    private List<EventBean> searchResults;
    private final String FONT = "System";

    public EventsViewGC(EventsView view) {
        this.view = view;
    }

    public int getResultSize(LocalDate date) throws EventException {
        this.searchResults = CalendarSubject.getInstance().getEntity(YearMonth.of(date.getYear(), date.getMonth())).getEvents(date) ;
        return this.searchResults.size() ;
    }

    public void setValues(SearchResultProduct searchResultProduct) {
        int i = 0;
        try {
            for (EventBean eventBean : searchResults) {
                Label nameLabel = new Label(eventBean.getName());
                nameLabel.setFont(new Font(FONT, 26));
                searchResultProduct.setEntry(i, 0, nameLabel);

                Label startTimeLabel = new Label(eventBean.getStart().toString());
                startTimeLabel.setFont(new Font(FONT, 26));
                searchResultProduct.setEntry(i, 1, startTimeLabel);

                Label endTimeLabel = new Label(eventBean.getEnd().toString());
                endTimeLabel.setFont(new Font(FONT, 26));
                searchResultProduct.setEntry(i, 2 , endTimeLabel);

                Label eventTypeLabel = new Label(eventBean.getType().charAt(0) + eventBean.getType().substring(1).toLowerCase().replace("_", " "));
                eventTypeLabel.setFont(new Font(FONT, 26));
                searchResultProduct.setEntry(i, 3, eventTypeLabel);

                Button description = new Button("Description");
                description.setOnAction(event -> {
                    SceneSwitcher switcher = new SceneSwitcher();
                    ArrayList<Object> params = new ArrayList<>() ;
                    params.add(eventBean.getDescription());
                    switcher.popup("EventDescription.fxml", "Event description", params);
                });

                searchResultProduct.setEntry(i, 4, description);

                Button delete = new Button("Delete");
                int finalI = i;
                delete.setOnAction(event -> {
                    try {
                        ManageEventUCC manageEventUCC = new ManageEventUCC();
                        manageEventUCC.deleteEvent(eventBean);
                        //searchResultProduct.deleteEntry(finalI) ;
                        this.view.refresh();
                    } catch (EventException exc) {
                        SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, exc.getMessage());
                        alert.showAndWait();
                        return;
                    }
                });
                searchResultProduct.setEntry(i, 5, delete);

                Button edit = new Button("edit");
                edit.setOnAction(event -> {
                    SceneSwitcher switcher = new SceneSwitcher();
                    ArrayList<Object> params = new ArrayList<>();
                    params.add(eventBean);
                    switcher.popup("AddEventScreen.fxml", "Edit your event", params);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                });
                searchResultProduct.setEntry(i, 6, edit);
                i++ ;
            }
        }
        catch (Exception exc) {

    }
}



}
