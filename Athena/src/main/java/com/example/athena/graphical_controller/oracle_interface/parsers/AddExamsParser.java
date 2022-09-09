package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.Student;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OracleAddExamsGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class AddExamsParser {
    
    public void parseAddExams(List<String> commandToken){
        LabelView view = new LabelView();
        
        try{
            if (Student.getInstance().getEmail() != null){
                if (commandToken.size() != 4){
                    ParentSubject.getInstance().setCurrentParent(view.prepareParent("you need to insert name cfus date and grade after add_exam command"));
                }
                else {
                    OracleAddExamsGC controller = new OracleAddExamsGC();
                    controller.addExam(commandToken.get(0), commandToken.get(1), commandToken.get(2), commandToken.get(3));
                }
            }
            else{
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("You need to login before writing commands"));
            }
        } catch (LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("only students can add exams"));
        }
    }
}
