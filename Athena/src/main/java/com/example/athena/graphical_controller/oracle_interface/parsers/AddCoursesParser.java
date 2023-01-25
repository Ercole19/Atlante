package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedTutor;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OracleAddCourseGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class AddCoursesParser {
    public void parseAddCourse(List<String> commandToken) {
        LabelView view = new LabelView();
        OracleAddCourseGC controller = new OracleAddCourseGC();
        try {
            if (LoggedTutor.getInstance().getEmail() != null) {
                if (commandToken.size() != 1) {
                    ParentSubject.getInstance().setCurrentParent(view.prepareParent("Incorrect arguments passed"));
                } else {
                    controller.addCourse(commandToken.get(0));
                }
            } else {
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("You must login first"));
            }
        } catch (LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Only tutors can add courses"));
        }
    }
}
