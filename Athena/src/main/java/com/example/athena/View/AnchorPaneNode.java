package com.example.athena.View;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

public class AnchorPaneNode extends AnchorPane {
    // Date associated with this pane
    private LocalDate date;
    private String event ;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> System.out.println("This pane's date is: " + date));
    }

    public LocalDate getDate() {
        return this.date;
    }
    public void setEvent (String event) {this.event = event;}
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
