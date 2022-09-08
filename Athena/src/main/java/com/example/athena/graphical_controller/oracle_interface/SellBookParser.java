package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.entities.Student;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import com.example.athena.view.oracle_view.LabelView;

public class SellBookParser {
    private LabelView view = new LabelView();
    public void parseSellBook () {
        try {
            if (Student.getInstance().getEmail() != null) {
                SceneSwitcher switcher = SceneSwitcher.getInstance();
                switcher.popup("sellBookModule.fxml", "Sell book");
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
