package com.example.athena.graphical_controller;

import com.example.athena.entities.ActivityTypesEnum;
import com.example.athena.entities.ReminderTypesEnum;
import com.example.athena.entities.StringHoursConverter;
import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.ManageEventUCC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class    AddEventController implements Initializable , PostInitialize{


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
    private EventBean oldEventBean;
    private Stage stage;
    private final ManageEventUCC controller = new ManageEventUCC();

    public void clickOnAddEvent(ActionEvent event){

        EventBean eventToRegister = new EventBean() ;

        try {
            setBean(eventToRegister);
            if ((eventToRegister.getStart().isAfter(eventToRegister.getEnd()))) {
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Event's start time must be before event's end");
                alert.showAndWait();
            }
            else if (setReminderCheckBox.isSelected() && (eventToRegister.getDateOfReminder().toLocalDateTime().isBefore(LocalDateTime.now()))) {
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Reminder's start time must be after actual time");
                alert.showAndWait();
            }
            else {
                controller.addEvent(eventToRegister);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        } catch (EventException e)
        {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800, 600);
            alert.showAndWait();
        }
        catch (SendEmailException ex)
        {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Unable to set notification to server. Try resending again by editing the event and updating", 800, 600) ;
            alert.showAndWait();

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
        EventBean eventToUpdate = new EventBean();
        try {
            setBean(eventToUpdate);
            controller.update(eventToUpdate, this.oldEventBean);
        }
        catch(EventException e){
            SizedAlert error = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800, 600);
            error.showAndWait();
        }
        catch(SendEmailException e)
        {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Error in setting the reminder. Try reopening the event and send it again", 800, 600);
            alert.showAndWait();
        }finally {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    private void setBean(EventBean bean) throws EventException {

        LocalTime start = LocalTime.of(startHourSpinner.getValue(), startMinuteSpinner.getValue());
        LocalTime end = LocalTime.of(endHourSpinner.getValue(), endMinuteSpinner.getValue());

        bean.setDate(eventDate.getValue());
        bean.setName(eventName.getText());
        bean.setStart(start);
        bean.setEnd(end);
        bean.setDescription(eventDescription.getText());
        bean.setType(eventType.getValue().toUpperCase().replace(" ", "_"));

        if (setReminderCheckBox.isSelected()) {
            switch (ReminderTypesEnum.valueOf(reminderType.getValue().toUpperCase().replace(" ", "_"))) {
                case HALF_AN_HOUR_BEFORE:
                    bean.setDateOfReminder(0, 30) ;
                    break ;
                case AN_HOUR_BEFORE:
                    bean.setDateOfReminder(1, 0);
                    break;
                case HALF_AND_AN_HOUR_BEFORE:
                    bean.setDateOfReminder(1,30);
                    break;
                case TWO_HOURS_BEFORE:
                    bean.setDateOfReminder(2,0);
                    break;
                case ONE_DAY_BEFORE:
                    bean.setDateOfReminder(24,0);
                    break;
                case CUSTOM:
                    bean.setDateOfReminder(reminderHour.getValue(), reminderMinute.getValue());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void postInitialize(ArrayList<Object> params) {

        this.oldEventBean = (EventBean) params.get(0);

        eventName.setText(this.oldEventBean.getName());
        eventDate.setValue(this.oldEventBean.getDate());
        startHourSpinner.getValueFactory().setValue(this.oldEventBean.getStart().getHour());
        startMinuteSpinner.getValueFactory().setValue(this.oldEventBean.getStart().getMinute());
        endHourSpinner.getValueFactory().setValue(this.oldEventBean.getEnd().getHour());
        endMinuteSpinner.getValueFactory().setValue(this.oldEventBean.getEnd().getMinute());
        eventDescription.setText(this.oldEventBean.getDescription());
        eventType.setValue(this.oldEventBean.getType());
        setReminderCheckBox.setSelected(this.oldEventBean.isThereAReminder());

        disable(eventDate);

        if(setReminderCheckBox.isSelected())
        {
            try {
                String custom = ReminderTypesEnum.CUSTOM.toString() ;
                reminderType.setValue(custom.charAt(0) + custom.substring(1).toLowerCase().replace("_", " ")) ;
                reminderHour.getValueFactory().setValue(this.oldEventBean.getDateOfReminder().toLocalDateTime().getHour());
                reminderMinute.getValueFactory().setValue(this.oldEventBean.getDateOfReminder().toLocalDateTime().getMinute());
            }
            catch(EventException e){
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800, 600);
                alert.showAndWait();
            }
        }

        confirm.setText("Update");

        confirm.setOnAction(this::updateEvent);
    }

}

