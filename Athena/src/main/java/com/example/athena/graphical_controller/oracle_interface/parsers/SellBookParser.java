package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OracleSellBookGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class SellBookParser {
    private LabelView view = new LabelView();
    public void parseSellBook (List<String> tokens) {
        if (tokens.size() != 3) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Incorrect arguments passed"));
        }
        else {
            try {
                if (LoggedStudent.getInstance().getEmail().getMail() != null) {
                    OracleSellBookGC controller = new OracleSellBookGC();
                    controller.sellBook(tokens.get(0), tokens.get(1), tokens.get(2));
                }
                else {
                    ParentSubject.getInstance().setCurrentParent(view.prepareParent("You must login first"));
                }
            }
            catch (LoggedUserException e) {
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("Only students can sell books"));
            }
        }
    }
}
