package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OracleAddEventGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class AddEventParser {
    private final LabelView view = new LabelView();
    public void parseAddEvent(List<String> commandToken){
        if (commandToken.size() != 4) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Incorrect usage"));
        }
        else {
            try{
                if (LoggedStudent.getInstance().getEmail() != null){
                    new OracleAddEventGC(commandToken.get(0), commandToken.get(1), commandToken.get(2), commandToken.get(3));
                }
                else{
                    ParentSubject.getInstance().setCurrentParent(view.prepareParent("You need to login before writing commands"));
                }
            } catch (LoggedUserException e) {
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("only students can modify events"));
            }
        }
    }
}
