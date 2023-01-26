package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.entities.ReminderTypesEnum;
import com.example.athena.exceptions.EventException;
import com.example.athena.view.oracle_view.OnSetReminderView;

public class OnSetReminderGC {

    private final OracleAddEventGC controller ;

    private final OnSetReminderView view ;

    public OnSetReminderGC(OracleAddEventGC controller) {
        this.controller = controller ;
        this.view = new OnSetReminderView() ;
        setUpInterface() ;
        ParentSubject.getInstance().setCurrentParent(this.view.getRoot()) ;
    }

    private void setUpInterface() {
        this.view.getButton().setOnAction(event -> onConfirmClick()) ;
    }

    public void onConfirmClick() {

        try {
            obtainReminder() ;
        } catch (EventException e) {
            this.view.getErrorLabel().setText(e.getMessage());
            this.view.getField().clear();
        }
    }

    private void obtainReminder() throws EventException{
        String reminder = view.getField().getText() ;

        int hoursBefore = 0;
        int minutesBefore = 0;
        try {
            if (reminder.matches("\\d{0,4}:[0-5]\\d")) {
                String[] tokens = reminder.split(":") ;
                hoursBefore = Integer.parseInt(tokens[0]) ;
                minutesBefore = Integer.parseInt(tokens[1]) ;
            } else {
                switch (ReminderTypesEnum.valueOf(reminder.toUpperCase().replace(" ", "_"))) {
                    case AN_HOUR_BEFORE :
                        hoursBefore = 1 ;
                        minutesBefore = 0 ;
                        break;
                    case ONE_DAY_BEFORE:
                        hoursBefore = 24 ;
                        minutesBefore = 0 ;
                        break;
                    case TWO_HOURS_BEFORE:
                        hoursBefore = 2 ;
                        minutesBefore = 0 ;
                        break;
                    case HALF_AN_HOUR_BEFORE:
                        hoursBefore = 0 ;
                        minutesBefore = 30 ;
                        break;
                    case HALF_AND_AN_HOUR_BEFORE:
                        hoursBefore = 1 ;
                        minutesBefore = 30 ;
                        break;
                    case CUSTOM:
                        throw new EventException("Wrong format") ;

                }
            }

            this.controller.setHoursBefore(hoursBefore) ;
            this.controller.setMinutesBefore(minutesBefore);

            this.controller.advance();
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new EventException("Wrong format") ;
        }
    }
}
