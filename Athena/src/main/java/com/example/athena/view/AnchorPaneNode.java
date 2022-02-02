package com.example.athena.view;

import com.example.athena.graphical_controller.SceneSwitcher;
import com.example.athena.view.scene_decorators.SearchResultFormatterComponent;
import com.example.athena.view.scene_decorators.SearchResultFormatterScrollBar;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import com.example.athena.graphical_controller.EventPageUcc;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.example.athena.graphical_controller.EventBean;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnchorPaneNode extends AnchorPane {
    // Date associated with this pane
    private LocalDate date;


    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        this.setOnMouseClicked(mouseEvent -> {
            try {

                EventPageUcc controller = new EventPageUcc() ;
                List<EventBean> results =  controller.formatSearchResultsByDate(this.getDate()) ; //Another bean should be added

                SceneSwitcher switcher = new SceneSwitcher() ;
                ArrayList<Object> params = new ArrayList<>() ;
                params.add(String.valueOf(this.getDate())) ;
                params.add(results) ;
                switcher.popup("eventPage.fxml", "Event infos", params) ;

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public LocalDate getDate() {
        return this.date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
