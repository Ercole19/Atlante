package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.normal.EventBean;
import com.example.athena.entities.ReminderTypesEnum;
import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.graphical_controller.oracle_interface.add_event_states.AddEventAbstractState;
import com.example.athena.graphical_controller.oracle_interface.add_event_states.OnSelectType;
import com.example.athena.use_case_controllers.ManageEventUCC;
import com.example.athena.view.oracle_view.InsertDescriptionView;
import com.example.athena.view.oracle_view.SelectTypeView;
import com.example.athena.view.oracle_view.SetReminderView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class OracleAddEventGC implements OnYesOrNoController{

    private String eventName ;
    private LocalDate eventDay ;
    private LocalTime eventStart ;
    private LocalTime eventEnd ;
    private String eventType ;
    private String eventDescription ;
    private int hoursBefore ;
    private int minutesBefore ;

    private boolean decision ;

    private AddEventAbstractState current ;

    public void addEvent(String name, String date, String start, String end ) {
        this.eventName = name ;
        this.eventDay = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")) ;
        this.eventStart = LocalTime.parse(start) ;
        this.eventEnd = LocalTime.parse(end) ;
        this.current = new OnSelectType(this) ;
    }

    public void obtainEventType(SelectTypeView view) {
        this.eventType = view.getChoiceBox().getValue().toUpperCase().replace(" ", "_") ;
        this.goNext();
    }

    public void obtainDescription(InsertDescriptionView view) {
        this.eventDescription = view.getDescription() ;
        this.goNext() ;
    }

    public void obtainReminder(SetReminderView view) throws EventException{
        String reminder = view.getReminderString() ;

        try {
            if (reminder.matches("\\d{0,4}:[0-5]\\d")) {
                String[] tokens = reminder.split(":") ;
                hoursBefore = Integer.parseInt(tokens[0]) ;
                minutesBefore = Integer.parseInt(tokens[1]) ;
            } else {
                switch (ReminderTypesEnum.valueOf(reminder.toUpperCase().replace(" ", "_"))) {
                    case AN_HOUR_BEFORE :
                        this.hoursBefore = 1 ;
                        this.minutesBefore = 0 ;
                        break;
                    case ONE_DAY_BEFORE:
                        this.hoursBefore = 24 ;
                        this.minutesBefore = 0 ;
                        break;
                    case TWO_HOURS_BEFORE:
                        this.hoursBefore = 2 ;
                        this.minutesBefore = 0 ;
                        break;
                    case HALF_AN_HOUR_BEFORE:
                        this.hoursBefore = 0 ;
                        this.minutesBefore = 30 ;
                        break;
                    case HALF_AND_AN_HOUR_BEFORE:
                        this.hoursBefore = 1 ;
                        this.minutesBefore = 30 ;
                        break;
                    case CUSTOM:
                        throw new EventException("Wrong format") ;

                }
            }

            this.goNext() ;

        } catch (IllegalArgumentException | NullPointerException e) {
            throw new EventException("Wrong format") ;
        }
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

    public void setState(AddEventAbstractState nextState) {
        this.current = nextState ;
    }

    public boolean getDecision() {
        return this.decision ;
    }

    public void goNext() {
        this.current.goNext(this) ;
    }

    @Override
    public void onYes() {
        this.decision = true ;
        this.goNext();
    }

    @Override
    public void onNo() {
        this.decision = false ;
        this.goNext();
    }
}
