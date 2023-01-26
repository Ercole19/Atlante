package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.EventBean;
import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.graphical_controller.oracle_interface.add_event_states.AddEventSM;
import com.example.athena.graphical_controller.oracle_interface.add_event_states.OnSelectType;
import com.example.athena.use_case_controllers.ManageEventUCC;
import com.example.athena.view.oracle_view.InsertDescriptionView;
import com.example.athena.view.oracle_view.OnSelectTypeView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class OracleAddEventGC {

    private final String eventName ;
    private final LocalDate eventDay ;
    private final LocalTime eventStart ;
    private final LocalTime eventEnd ;
    private String eventType ;
    private String eventDescription ;
    private int hoursBefore ;
    private int minutesBefore ;

    private boolean decision ;

    private final AddEventSM machine ;

    public OracleAddEventGC (String name, String date, String start, String end ) {
        this.eventName = name ;
        this.eventDay = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")) ;
        this.eventStart = LocalTime.parse(start) ;
        this.eventEnd = LocalTime.parse(end) ;
        this.machine = new AddEventSM(this, new OnSelectType(this)) ;
    }

    public void saveEvent() throws EventException, SendEmailException {
        EventBean bean = new EventBean() ;
        bean.setDate(this.eventDay) ;
        bean.setStart(this.eventStart);
        bean.setEnd(this.eventEnd) ;
        bean.setName(this.eventName) ;
        bean.setType(this.eventType);
        if(this.eventDescription != null) {
            bean.setDescription(this.eventDescription) ;
        } else {
            bean.setDescription("");
        }

        if (this.minutesBefore != 0 || this.hoursBefore != 0) {
            bean.setDateOfReminder(this.hoursBefore, this.minutesBefore);
        }

        new ManageEventUCC().addEvent(bean);
    }

    public boolean getDecision() {
        return this.decision ;
    }

    public void advance() {
        this.machine.goNext() ;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setHoursBefore(int hoursBefore) {
        this.hoursBefore = hoursBefore;
    }

    public void setMinutesBefore(int minutesBefore) {
        this.minutesBefore = minutesBefore;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }
}
