package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.FindBooksView;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class ShowBooksParser {

    public void parseShowBooks(List<String> commandToken) {
        LabelView labelView = new LabelView();
        if (commandToken.size() != 1) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Incorrect arguments passed"));
        }
        else  {
            try {
                if (LoggedStudent.getInstance().getEmail() != null) {
                    FindBooksView view = new FindBooksView(1200, 560);
                    ParentSubject.getInstance().setCurrentParent(view.getRoot(commandToken.get(0)));
                } else {
                    ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("You must login before writing any command"));
                }
            }
            catch(LoggedUserException e){
                ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Only students can find books"));
            }
        }
    }
}
