package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.Student;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.CfusCompletion;
import com.example.athena.view.oracle_view.LabelView;
import com.example.athena.view.oracle_view.TakenExams;

public class ShowCfusCompletionParser {

    public void parseCfusCompletion() {
        LabelView labelView = new LabelView();
        try {
            if (Student.getInstance().getEmail() != null) {
                CfusCompletion view = new CfusCompletion();
                ParentSubject.getInstance().setCurrentParent(view.getParent());
            } else {
                ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("You must login before writing any command"));
            }
        }
        catch(LoggedUserException e){
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Only students can see their cfus completion"));
        }
    }
}
