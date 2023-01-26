package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedTutor;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OracleAddCvGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class AddCvParser {
    
    public void parseAddCV(List<String> commandTokens) {
        LabelView view = new LabelView();
        if(LoggedTutor.getInstance().getEmail().getMail() == null){
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("you must login before writing commands"));
        }
        else {
            if (commandTokens.size() != 1 || !(commandTokens.get(0).endsWith(".html"))) {
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("Incorrect arguments passed"));
            } else {
                try {
                    OracleAddCvGC controller = new OracleAddCvGC();
                    controller.addCv(commandTokens.get(0));
                } catch (LoggedUserException e) {
                    ParentSubject.getInstance().setCurrentParent(view.prepareParent("Only tutors can add a cv"));
                }
            }
        }
    }
    
}
