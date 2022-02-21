package com.example.athena.view;

import com.example.athena.entities.CalendarEntity;
import com.example.athena.graphical_controller.CalendarPageController;
import com.example.athena.graphical_controller.EventBean;
import com.example.athena.graphical_controller.SceneSwitcher;
import com.example.athena.use_case_controllers.EventPageUCC;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnchorPaneNode extends AnchorPane {

    private LocalDate date;
    private CalendarPageController controller;

    public AnchorPaneNode(CalendarPageController controller, Node... children) {
        super(children);
        this.controller = controller ;
        this.setOnMouseClicked(mouseEvent -> controller.loadEventsByDate(this.date)) ;
    }

    public LocalDate getDate() {
        return this.date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
