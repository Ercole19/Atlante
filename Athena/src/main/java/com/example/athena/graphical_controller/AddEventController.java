package com.example.athena.graphical_controller;

import com.example.athena.entities.StringHoursConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.athena.use_case_controllers.addEventUCC ;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEventController implements Initializable  {



        @FXML
        private Spinner<Integer> startHourSpinner ;

        @FXML
        private Spinner<Integer> startMinuteSpinner ;

        @FXML
        private Spinner<Integer> endHourSpinner ;

        @FXML
        private Spinner<Integer> endMinuteSpinner ;

        @FXML
        private DatePicker eventDate;

        @FXML
        private TextField eventDescription;

        @FXML
        private TextField eventName;





    private Stage stage;

    public void clickOnAddEvent(ActionEvent event) throws IOException
    {

        eventBean eventt = new eventBean() ;
        eventt.setDate(eventDate.getValue());
        eventt.setName(eventName.getText());
        eventt.setStart(startHourSpinner.getValue() , startMinuteSpinner.getValue());
        eventt.setEnd(endHourSpinner.getValue() , endMinuteSpinner.getValue());
        eventt.setDescription(eventDescription.getText());
        addEventUCC addEventUCC = new addEventUCC() ;
        addEventUCC.addEvent(eventt);



        stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        stage.close() ;
    }

    public void clickOnX(ActionEvent event) throws IOException
    {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        stage.close() ;
    }
    private void prepareFactory(Spinner<Integer> spinner, int rangeStart, int rangeEnd)
    {
        SpinnerValueFactory<Integer> minutesValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(rangeStart, rangeEnd) ;
        minutesValueFactory.setWrapAround(true) ;
        minutesValueFactory.setConverter(new StringHoursConverter(rangeStart, rangeEnd)) ;
        minutesValueFactory.setValue(0) ;

        spinner.setValueFactory(minutesValueFactory) ;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        prepareFactory(startHourSpinner, 0, 23) ;
        prepareFactory(startMinuteSpinner, 0, 59) ;
        prepareFactory(endHourSpinner, 0, 23) ;
        prepareFactory(endMinuteSpinner, 0, 59) ;
    }
}
