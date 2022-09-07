package com.example.athena.view;

import com.example.athena.graphical_controller.normal_interface.CalendarPageController;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

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
