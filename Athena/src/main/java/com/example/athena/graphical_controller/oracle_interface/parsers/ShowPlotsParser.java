package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.Student;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

public class ShowPlotsParser {
    private final LabelView view = new LabelView() ;
    SceneSwitcher switcher = SceneSwitcher.getInstance();
    public void parseShowPlots() {
        try{
            if (Student.getInstance().getEmail() != null){
                ParentSubject.getInstance().setCurrentParent(switcher.preload("OraclePlotPage.fxml"));
            }
            else{
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("You must login to see your plots"));
            }
        } catch (LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("only students can see their events plots"));
        }
    }
}
