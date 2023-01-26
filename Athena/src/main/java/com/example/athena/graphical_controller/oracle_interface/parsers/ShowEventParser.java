package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.EventsViewGC;
import com.example.athena.graphical_controller.oracle_interface.OracleEventsViewGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ShowEventParser {
    public void showEventParse(List<String> commandToken) {
        LabelView labelView = new LabelView();
        if (commandToken.size() != 1) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("must insert date after show events command"));
            return;
        }
        try {
            if (LoggedStudent.getInstance().getEmail().getMail() != null) {
                EventsViewGC helpController = new OracleEventsViewGC(1021, 561);
                ParentSubject.getInstance().setCurrentParent(helpController.getRoot(LocalDate.parse(commandToken.get(0), DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            } else {
                ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("You must login before writing any command"));
            }
        }
        catch(LoggedUserException e){
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Only students can manage their events"));
        } catch (DateTimeParseException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Incorrect date format.\nCorrect format is: dd/MM/yyyy"));
        }
    }
}
