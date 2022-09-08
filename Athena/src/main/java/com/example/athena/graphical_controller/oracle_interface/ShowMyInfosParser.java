package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.entities.Tutor;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import com.example.athena.view.oracle_view.LabelView;
import com.example.athena.view.oracle_view.MyInfosView;

public class ShowMyInfosParser {

    public void parseMyInfos() {
        LabelView view = new LabelView();
        try{
            if (Tutor.getInstance().getEmail() != null) {
                MyInfosView infoView = new MyInfosView();
                ParentSubject.getInstance().setCurrentParent(infoView.prepareView());
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
