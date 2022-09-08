package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.graphical_controller.normal_interface.ValidateSignUpGC;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class ValidateSignUpParser {

    public void parseValidateSignUp(List<String> commandToken){
        OracleValidateSignUpGC  controller = new OracleValidateSignUpGC();
        if (commandToken.size() < 2){
            LabelView view = new LabelView();
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("insert email and code after validate command"));
        } else {
            controller.createParent(commandToken.get(0), commandToken.get(1));
        }
    }
}
