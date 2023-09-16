package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OracleSetMaxExamsGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class SetMaxExamsParser {
    
    public void parseSetMaxExams(List<String> commandToken) {
        LabelView view = new LabelView();
        OracleSetMaxExamsGC controller = new OracleSetMaxExamsGC();
        try {
            if (LoggedStudent.getInstance().getEmail().getMail() != null) {
                controller.setMaxExams(commandToken.get(0));
            } else {
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("You need to login before writing commands"));
            }
        } catch (LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("only students can modify max exams"));
        }
    }
}
