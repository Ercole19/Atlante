package com.example.athena.graphical_controller;

import com.example.athena.entities.StringHoursConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.athena.use_case_controllers.AddEventUCC;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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

        private boolean update ;
        private String oldEventName ;





    private Stage stage;

    public void clickOnAddEvent(ActionEvent event) throws IOException
    {

        EventBean eventt = new EventBean() ;
        eventt.setDate(eventDate.getValue());
        eventt.setName(eventName.getText());
        eventt.setStart(startHourSpinner.getValue() , startMinuteSpinner.getValue());
        eventt.setEnd(endHourSpinner.getValue() , endMinuteSpinner.getValue());
        eventt.setDescription(eventDescription.getText());

        if (eventt.getDescription().length() > 50 | eventt.getStart().isAfter(eventt.getEnd()) | eventt.getName().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR , "Data not valid" , ButtonType.CLOSE) ;
            alert.showAndWait();
        }
        else {
            AddEventUCC addEventUCC = new AddEventUCC();
            addEventUCC.addEvent(eventt, update, oldEventName);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
            stage.close() ;
        }



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

    public void setOldEventName(String oldname) {
        oldEventName = oldname ;
    }


    public void setEventName(String nome) {
        eventName.setText(nome);
    }

    public void setEventDate(LocalDate data) {
        eventDate.setValue( data);
    }

    public void setEventDescription(String description) {
        eventDescription.setText(description);
    }

    public void setStartHourSpinner(int startHour) {
        startHourSpinner.getValueFactory().setValue(startHour);
    }

    public void setStartMinuteSpinner(int startMinute) {
        startMinuteSpinner.getValueFactory().setValue(startMinute);
    }

    public void setEndHourSpinner(int endHour) {
        endHourSpinner.getValueFactory().setValue(endHour);
    }

    public void setEndMinuteSpinner(int endMinute) {
        endMinuteSpinner.getValueFactory().setValue(endMinute);
    }
    public void setUpdate (boolean cond) {
        update = cond;

    }
}
