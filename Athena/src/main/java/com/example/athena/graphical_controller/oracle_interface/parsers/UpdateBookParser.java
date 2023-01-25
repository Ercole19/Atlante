package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OracleUpdateBookGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class UpdateBookParser {
    public void parseUpdateBook(List<String> tokens) {
        LabelView view = new LabelView();
        if (tokens.size() != 1) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Correct usage: update_book#isbn"));
        }
        else {
            try {
                if (LoggedStudent.getInstance().getEmail() != null) {
                    OracleUpdateBookGC controller = new OracleUpdateBookGC();
                    controller.updateBook(tokens.get(0));
                }
                else {ParentSubject.getInstance().setCurrentParent(view.prepareParent("Must login before using commands"));}
            }
            catch (LoggedUserException e) {
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("Only students can put books on sale"));
            }
        }
    }
}
