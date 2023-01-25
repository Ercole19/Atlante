package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.oracle_interface.OracleDeleteExamGC;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class DeleteExamParser {

    public void parseDeleteExams(List<String> commandToken){
        LabelView view = new LabelView();
        OracleDeleteExamGC controller = new OracleDeleteExamGC();
        try{
            if (LoggedStudent.getInstance().getEmail() != null){
                if (commandToken.size() != 4){
                    ParentSubject.getInstance().setCurrentParent(view.prepareParent("Correct usage: exam_name exam_date"));
                }
                else
                {
                    controller.deleteExam(commandToken.get(0), commandToken.get(1), commandToken.get(2), commandToken.get(3));
                    ParentSubject.getInstance().setCurrentParent(view.prepareParent("Exam deleted"));
                }
            }
            else{
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("You need to login before writing commands"));
            }
        } catch (LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("only students can delete exams"));
        }
    }
    }

