package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OraclePlotGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class ShowPlotsParser {
    private final LabelView view = new LabelView() ;
    public void parseShowPlots(List<String> commands) {
        try{
            if (LoggedStudent.getInstance().getEmail().getMail() != null){
                new OraclePlotGC(commands) ;
            }
            else{
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("You must login to see your plots"));
            }
        } catch (LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Only students can see their events plots"));
        }
    }
}
