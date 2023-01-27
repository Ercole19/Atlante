package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.EventBean;
import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.graphical_controller.oracle_interface.edit_event_states.EditEventSM;
import com.example.athena.graphical_controller.oracle_interface.edit_event_states.OnSelectWhatToEdit;
import com.example.athena.use_case_controllers.ManageEventUCC;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class OracleEditEventGC {
    private final EditEventSM machine ;

    private final EventBean bean ;

    private final EventBean oldBean ;

    public OracleEditEventGC(EventBean eventToEdit) {
        this.bean = eventToEdit ;
        this.machine = new EditEventSM(this, new OnSelectWhatToEdit(this)) ;
        this.oldBean = new EventBean() ;
        try {
            this.oldBean.setName(eventToEdit.getName());
            this.oldBean.setDate(eventToEdit.getDate()) ;
            this.oldBean.setStart(eventToEdit.getStart()) ;
            this.oldBean.setEnd(eventToEdit.getEnd()) ;
            this.oldBean.setDescription(eventToEdit.getDescription()) ;
            this.oldBean.setType(eventToEdit.getType()) ;
            if (eventToEdit.isThereAReminder()) {
                LocalDateTime reminder = eventToEdit.getDateOfReminder() ;
                LocalDateTime eventStart = LocalDateTime.of(eventToEdit.getDate(), eventToEdit.getStart()) ;
                long distance = reminder.until(eventStart, ChronoUnit.MINUTES) ;
                int minutesBefore = (int)distance % 60 ;
                int hoursBefore = (int)distance / 60 ;
                this.oldBean.setDateOfReminder(hoursBefore, minutesBefore) ;
            }
        } catch (EventException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Fatal error. Wrong internal data representation. Exiting", ButtonType.OK) ;
            alert.showAndWait() ;
            System.exit(1) ;
        }
    }

    public void advance() {
        this.machine.goNext() ;
    }


    public LocalTime getEventStart() {
        return bean.getStart();
    }

    public LocalTime getEnd() {
        return bean.getEnd();
    }

    public String getDescription() {
        return bean.getDescription();
    }

    public String getType() {
        return bean.getType() ;
    }

    public LocalDateTime getDateOfReminder() throws EventException{
        return bean.getDateOfReminder() ;
    }

    public LocalDate getEventDay() {
        return bean.getDate() ;
    }


    public void setType(String type)
    {
        bean.setType(type.toUpperCase().replace(" ", "_")) ;
    }

    public void setStart(LocalTime start) {
        bean.setStart(start) ;
    }


    public void setEnd(LocalTime end) {
        bean.setEnd(end) ;
    }

    public void setDescription(String description) throws EventException{
        bean.setDescription(description) ;
    }

    public void setReminder(int hoursBefore, int minutesBefore) {
        this.bean.setDateOfReminder(hoursBefore, minutesBefore) ;
    }

    public void updateEvent() {
        try {
            ManageEventUCC controller = new ManageEventUCC() ;
            controller.update(bean, oldBean) ;
        } catch (EventException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Error in updating event. Details: " + e.getMessage(), ButtonType.OK) ;
            alert.showAndWait() ;
        } catch (SendEmailException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Error in communication with mail server. Re-edit the event to retry", ButtonType.OK) ;
            alert.showAndWait() ;
        }
    }
}
