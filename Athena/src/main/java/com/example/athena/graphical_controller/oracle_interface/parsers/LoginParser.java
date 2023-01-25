package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.graphical_controller.oracle_interface.OracleLoginGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;


public class LoginParser {

    public void parseLogin(List<String> commandToken) {
        if (commandToken.size() != 1){
            LabelView view = new LabelView();
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("insert email and password after login command"));
        } else {
            new OracleLoginGC(commandToken.get(0));
        }
    }
}
