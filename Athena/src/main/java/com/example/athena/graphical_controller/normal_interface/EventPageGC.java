package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.view.EventsView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import java.time.LocalDate;
import java.util.ArrayList;


public class EventPageGC implements PostInitialize {
    @FXML
    private Label label1 ;
    @FXML
    private SubScene results ;

    @Override
    public void postInitialize(ArrayList<Object> params) {
        EventsView eventsView = new EventsView(results.getWidth(), results.getHeight());
        label1.setText(String.valueOf(params.get(0))) ;
        LocalDate dayToLoad = (LocalDate) params.get(0);
        this.results.setRoot(eventsView.getRoot(dayToLoad));
    }

    public void close() {
        SceneSwitcher.getInstance().getTopStage().close();
    }
}
