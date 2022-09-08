package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.entities.Tutor;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import com.example.athena.view.oracle_view.LabelView;

public class ShowMyInfosParser {

    public void parseMyInfos() {
        LabelView view = new LabelView();
        try{
            if (Tutor.getInstance().getEmail() != null) {
                new ShowMyInfosGC();
            }
            else {
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("You must login before writing any command"));
            }
        }catch (
        LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Only tutors can modify their infos"));
        }
    }
}
