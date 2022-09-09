package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.graphical_controller.oracle_interface.OracleSignUpGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;
import javafx.scene.Parent;

import java.util.List;

public class SignUpParser {

    public void parseSignUp(List<String> commandToken){
        if (commandToken.size() < 4) {
            LabelView view = new LabelView();
            Parent product = view.prepareParent("insert name surname email and password after signup command");
            ParentSubject.getInstance().setCurrentParent(product);
        }
        else
        {
            new OracleSignUpGC(commandToken.get(0), commandToken.get(1), commandToken.get(2), commandToken.get(3));
        }
    }
}
