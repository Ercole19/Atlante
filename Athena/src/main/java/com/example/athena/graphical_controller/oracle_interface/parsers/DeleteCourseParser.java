package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.Tutor;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OracleDeleteCourseGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class DeleteCourseParser {
    public void parseDeleteCourse(List<String> commandToken) {
        LabelView view = new LabelView();
        OracleDeleteCourseGC controller = new OracleDeleteCourseGC();
        try {
            if (Tutor.getInstance().getEmail() != null) {
                if (commandToken.size() != 1) {
                    ParentSubject.getInstance().setCurrentParent(view.prepareParent("Incorrect arguments passed"));
                } else {
                    controller.deleteCourse(commandToken.get(0));
                }
            } else {
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("You must login first"));
            }
        } catch (LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Only tutors can add courses"));
        }
    }
}
