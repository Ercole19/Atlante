package com.example.athena.graphical_controller;

import com.example.athena.entities.ActivityTypesEnum;
import com.example.athena.entities.ReminderTypesEnum;
import com.example.athena.entities.StringHoursConverter;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.use_case_controllers.AddEventUCC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddEventController implements Initializable , PostInitialize{


    @FXML
    private Spinner<Integer> startHourSpinner;

    @FXML
    private Spinner<Integer> startMinuteSpinner;

    @FXML
    private Spinner<Integer> endHourSpinner;

    @FXML
    private Spinner<Integer> endMinuteSpinner;

    @FXML
    private DatePicker eventDate;

    @FXML
    private TextField eventDescription;

    @FXML
    private TextField eventName;

    @FXML
    private ChoiceBox<String> eventType;

    @FXML
    private CheckBox setReminderCheckBox;

    @FXML
    private ChoiceBox<String> reminderType;

    @FXML
    private Spinner<Integer> reminderHour;

    @FXML
    private Spinner<Integer> reminderMinute;

    @FXML
    private Text hoursText;

    @FXML
    private Text minutesText;
    @FXML
    private Button confirm;

    private String oldEventName;
    private EventReminderWrapperBean wrapperBean;


    private Stage stage;

    public void clickOnAddEvent(ActionEvent event){

        if (setReminderCheckBox.isSelected()) {
            switch (ReminderTypesEnum.valueOf(reminderType.getValue().toUpperCase().replace(" ", "_"))) {
                case AN_HOUR_BEFORE:
                    wrapperBean = new EventReminderWrapperBean(true, 1, 0);
                    break;
                case HALF_AND_AN_HOUR_BEFORE:
                    wrapperBean = new EventReminderWrapperBean(true, 1, 30);
                    break;
                case TWO_HOURS_BEFORE:
                    wrapperBean = new EventReminderWrapperBean(true, 2, 0);
                    break;
                case ONE_DAY_BEFORE:
                    wrapperBean = new EventReminderWrapperBean(true, 24, 0);
                    break;
                case CUSTOM:
                    wrapperBean = new EventReminderWrapperBean(true, reminderHour.getValue(), reminderMinute.getValue());
                    break;
                default:
                    wrapperBean = new EventReminderWrapperBean(false);
                    break;
            }
        } else {
            wrapperBean = new EventReminderWrapperBean(false);
        }

        LocalTime start = LocalTime.of(startHourSpinner.getValue(), startMinuteSpinner.getValue());
        LocalTime end = LocalTime.of(endHourSpinner.getValue(), endMinuteSpinner.getValue());
        EventBean eventToRegister = new EventBean(eventDate.getValue(), eventName.getText(), start, end, eventDescription.getText(), eventType.getValue(), wrapperBean);

        if (eventToRegister.getDescription().length() > 50 | eventToRegister.getStart().isAfter(eventToRegister.getEnd()) | eventToRegister.getName().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data not valid", ButtonType.CLOSE);
            alert.showAndWait();

        } else {
            AddEventUCC addEventUCC = new AddEventUCC();
            try {
                addEventUCC.addEvent(eventToRegister);
            } catch (SendEmailException e) {
                Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
                error.showAndWait();
            }

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }


    }

    public void clickOnX(ActionEvent event){
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void prepareFactory(Spinner<Integer> spinner, int rangeStart, int rangeEnd) {
        SpinnerValueFactory<Integer> minutesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(rangeStart, rangeEnd);
        minutesValueFactory.setWrapAround(true);
        minutesValueFactory.setConverter(new StringHoursConverter(rangeStart, rangeEnd));
        minutesValueFactory.setValue(0);

        spinner.setValueFactory(minutesValueFactory);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepareFactory(startHourSpinner, 0, 23);
        prepareFactory(startMinuteSpinner, 0, 59);
        prepareFactory(endHourSpinner, 0, 23);
        prepareFactory(endMinuteSpinner, 0, 59);
        prepareFactory(reminderHour, 0, 9999);
        prepareFactory(reminderMinute, 0, 60);

        for (ActivityTypesEnum type : ActivityTypesEnum.values()) {
            String name = type.name();
            eventType.getItems().add(name.charAt(0) + name.substring(1).toLowerCase().replace("_", " "));
        }

        eventType.setValue(eventType.getItems().get(0));

        for (ReminderTypesEnum type : ReminderTypesEnum.values()) {
            String name = type.name();
            reminderType.getItems().add(name.charAt(0) + name.substring(1).toLowerCase().replace("_", " "));
        }

        reminderType.setValue(reminderType.getItems().get(0));

        toggleReminderChoiceBox(false);
        toggleReminderElements(false);

        setReminderCheckBox.setOnAction(event ->
                toggleReminderChoiceBox(setReminderCheckBox.isSelected()));

        reminderType.setOnAction(event ->
                toggleReminderElements(reminderType.getValue().equals("Custom")));
    }

    private void disable(Node node) {
        node.setVisible(false);
        node.setDisable(true);
    }

    private void enable(Node node) {
        node.setVisible(true);
        node.setDisable(false);
    }

    private void toggleReminderChoiceBox(boolean isEnabled) {
        if (isEnabled) {
            enable(reminderType);
            if (reminderType.getValue().equals("Custom")) toggleReminderElements(true);
        } else {
            disable(reminderType);
            toggleReminderElements(false);
        }
    }

    private void toggleReminderElements(boolean isEnabled) {
        if (isEnabled) {
            enable(reminderHour);
            enable(reminderMinute);
            enable(hoursText);
            enable(minutesText);
        } else {
            disable(reminderHour);
            disable(reminderMinute);
            disable(hoursText);
            disable(minutesText);
        }
    }

    public void updateEvent (ActionEvent event) {
        AddEventUCC controller = new AddEventUCC() ;
        LocalTime start = LocalTime.of(startHourSpinner.getValue(), startMinuteSpinner.getValue());
        LocalTime end = LocalTime.of(endHourSpinner.getValue(), endMinuteSpinner.getValue());
        if (setReminderCheckBox.isSelected()){
            wrapperBean = new EventReminderWrapperBean(true,reminderHour.getValue() , reminderMinute.getValue());
        }
        else {
            wrapperBean = new EventReminderWrapperBean(false);
        }
        EventBean eventToUpdate = new EventBean(eventDate.getValue(), eventName.getText(), start, end, eventDescription.getText(), eventType.getValue(), wrapperBean);

        controller.update(eventToUpdate, oldEventName);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void postInitialize(ArrayList<Object> params) {

        EventBean event = (EventBean) params.get(0);
        oldEventName = event.getName();

        eventName.setText(event.getName());
        eventDate.setValue(event.getDate());
        startHourSpinner.getValueFactory().setValue(event.getStart().getHour());
        startMinuteSpinner.getValueFactory().setValue(event.getStart().getMinute());
        endHourSpinner.getValueFactory().setValue(event.getEnd().getHour());
        endMinuteSpinner.getValueFactory().setValue(event.getEnd().getMinute());
        eventDescription.setText(event.getDescription());
        eventType.setValue(event.getType());
        setReminderCheckBox.setSelected(event.getIsThereAReminder());

        if(setReminderCheckBox.isSelected())
        {
            reminderHour.getValueFactory().setValue(event.getDateOfReminder().getHour());
            reminderMinute.getValueFactory().setValue(event.getDateOfReminder().getMinute());
        }


        confirm.setText("Update");

        confirm.setOnAction(this::updateEvent);
    }
}

