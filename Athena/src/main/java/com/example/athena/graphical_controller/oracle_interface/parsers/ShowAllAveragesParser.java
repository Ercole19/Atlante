package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.AverageView;
import com.example.athena.view.oracle_view.LabelView;

public class ShowAllAveragesParser {

    public void parseAllAverages() {
        LabelView labelView = new LabelView();
            try {
                if (LoggedStudent.getInstance().getEmail() != null) {
                    AverageView view = new AverageView();
                    ParentSubject.getInstance().setCurrentParent(view.getParent());
                } else {
                    ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("You must login before writing any command"));
                }
            }
            catch(LoggedUserException e){
                ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Only students can see their average"));
            }
        }
    }
