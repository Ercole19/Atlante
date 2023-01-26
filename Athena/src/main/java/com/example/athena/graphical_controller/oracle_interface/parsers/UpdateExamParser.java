package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OracleUpdateExamGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class UpdateExamParser {
    public void parseUpdateExam(List<String> commandTokens) {
        LabelView view = new LabelView();
        try {
            if (LoggedStudent.getInstance().getEmail().getMail() != null) {
                if (commandTokens.size() != 8) {
                    ParentSubject.getInstance().setCurrentParent(view.prepareParent("Correct usage: old _name old_date old_grade old_cfu new_name new_date new_grade new_cfu"));
                } else {
                    OracleUpdateExamGC controller = new OracleUpdateExamGC();
                    controller.updateExam(commandTokens);
                }
            } else {
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("User not logged, you must login first"));
            }
        } catch (LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Only students can update an exam"));
        }
    }
}
