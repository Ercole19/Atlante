package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.Student;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.EventsView;
import com.example.athena.view.oracle_view.LabelView;

import java.time.LocalDate;
import java.util.List;

public class ShowEventParser {
    public void showEventParse(List<String> commandToken) {
        LabelView labelView = new LabelView();
        if (commandToken.size() != 1) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("must insert date after show events command"));
        }
        try {
            if (Student.getInstance().getEmail() != null) {
                EventsView view = new EventsView(1200, 560);
                ParentSubject.getInstance().setCurrentParent(view.getRoot(LocalDate.parse(commandToken.get(0))));
            } else {
                ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("You must login before writing any command"));
            }
        }
        catch(LoggedUserException e){
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Only students can manage their events"));
        }
    }
}
