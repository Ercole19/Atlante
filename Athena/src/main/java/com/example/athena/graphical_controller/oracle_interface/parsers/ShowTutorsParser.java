package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.ByCourseOrNameEnum;
import com.example.athena.entities.Student;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.SearchTutorView;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class ShowTutorsParser {

    public void parseShowTutor(List<String> commandToken) {
        LabelView labelView = new LabelView();
        if (commandToken.size() != 3 || !((commandToken.get(2).equals("true")) || (commandToken.get(2).equals("false"))) ) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Incorrect arguments passed"));
        }
        else  {
            try {
                if (Student.getInstance().getEmail() != null) {
                    SearchTutorView view = new SearchTutorView(1020, 560);
                    if (commandToken.get(0).equals("byName")) {
                        ParentSubject.getInstance().setCurrentParent(view.getRoot(commandToken.get(1), ByCourseOrNameEnum.BY_NAME, Boolean.parseBoolean(commandToken.get(2))));
                    } else if (commandToken.get(0).equals("byCourse")){
                        ParentSubject.getInstance().setCurrentParent(view.getRoot(commandToken.get(1), ByCourseOrNameEnum.BY_COURSE, Boolean.parseBoolean(commandToken.get(2))));}
                    else {
                        ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Incorrect syntax"));
                    }
                }
                else {
                    ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("you must login to write commands"));
                }
            }
            catch(LoggedUserException e){
                ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Only students can find tutors"));
            }
        }
    }
}
