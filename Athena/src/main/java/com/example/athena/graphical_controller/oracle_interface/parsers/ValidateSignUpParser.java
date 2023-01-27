package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.graphical_controller.oracle_interface.OracleValidateSignUpGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class ValidateSignUpParser {

    public void parseValidateSignUp(List<String> commandToken){
        OracleValidateSignUpGC controller = new OracleValidateSignUpGC();
        if (commandToken.size() < 2){
            LabelView view = new LabelView();
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Usage: validate_signup#email#code"));
        } else {
            controller.createParent(commandToken.get(0), commandToken.get(1));
        }
    }
}
