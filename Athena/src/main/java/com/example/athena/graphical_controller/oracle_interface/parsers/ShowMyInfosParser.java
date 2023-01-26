package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedTutor;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;
import com.example.athena.view.oracle_view.MyInfosView;

public class ShowMyInfosParser {

    public void parseMyInfos() {
        LabelView view = new LabelView();
        try{
            if (LoggedTutor.getInstance().getEmail().getMail() != null) {
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
