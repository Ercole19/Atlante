package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.Student;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

public class AddEventParser {
    private final LabelView view = new LabelView();
    public void parseAddEvent(){
        try{
            if (Student.getInstance().getEmail() != null){
                SceneSwitcher switcher = SceneSwitcher.getInstance();
                switcher.popup("AddEventScreen.fxml", "Add Event");
            }
            else{
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("You need to login before writing commands"));
            }
        } catch (LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("only students can modify events"));
        }
    }
}
