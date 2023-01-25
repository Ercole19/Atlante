package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedTutor;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;
import com.example.athena.view.oracle_view.MyCoursesView;

public class ShowMyCoursesParser {

    public void parseMyCourses(){
        LabelView labelView = new LabelView();
        try{
            if (LoggedTutor.getInstance().getEmail() != null) {
                MyCoursesView coursesView = new MyCoursesView();
                ParentSubject.getInstance().setCurrentParent(coursesView.createParent());
            }
            else {
                ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("You must login before writing any command"));
            }
        }catch (
                LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Only tutors can modify their infos"));
        }

    }
}
