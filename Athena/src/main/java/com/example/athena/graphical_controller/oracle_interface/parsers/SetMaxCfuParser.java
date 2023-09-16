package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OracleSetMaxCfusGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class SetMaxCfuParser {
    public void parseSetMaxCfus(List<String> commandTokens) {
        LabelView view = new LabelView();
        OracleSetMaxCfusGC controller = new OracleSetMaxCfusGC();
        try{
            if (LoggedStudent.getInstance().getEmail().getMail() != null){
                controller.setMaxCfus(commandTokens.get(0));
            }
            else{
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("You need to login before writing commands"));
            }
        } catch (LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("only students can modify max cfus"));
        }

    }
}
