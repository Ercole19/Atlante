package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OracleDeleteBookGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class DeleteBookParser {
    public void parseDeleteBook(List<String> tokens) {
        LabelView view = new LabelView();
        if (tokens.size() != 1) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Correct usage: delete_book#isbn"));
        }
        else {
            try {
                if (LoggedStudent.getInstance().getEmail().getMail() != null) {
                    OracleDeleteBookGC controller = new OracleDeleteBookGC();
                    controller.deleteBook(tokens.get(0));
                }
                else {ParentSubject.getInstance().setCurrentParent(view.prepareParent("Must login before using commands"));}
            }
            catch (LoggedUserException e) {
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("Only students can put books on sale"));
            }
        }
    }
}

