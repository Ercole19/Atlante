package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.OracleExamBean;
import com.example.athena.exceptions.ExamException;
import com.example.athena.use_case_controllers.ManageExamsUCC;
import com.example.athena.view.oracle_view.LabelView;

import java.util.List;

public class OracleUpdateExamGC {
    public void updateExam(List<String> tokens) {
        LabelView view = new LabelView();
        OracleExamBean oldBook = new OracleExamBean();
        OracleExamBean newBook = new OracleExamBean();
        ManageExamsUCC controller = new ManageExamsUCC();
        try {
            oldBook.setExamName(tokens.get(0));
            oldBook.setExamDate(tokens.get(1));
            oldBook.setExamGrade(tokens.get(2));
            oldBook.setExamCfus(tokens.get(3));

            newBook.setExamName(tokens.get(4));
            newBook.setExamDate(tokens.get(5));
            newBook.setExamGrade(tokens.get(6));
            newBook.setExamCfus(tokens.get(7));
            controller.updateExamFromDB(newBook, oldBook);
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Exam updated"));
            
        }
        catch (ExamException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in updating book, details follow: " + e.getMessage()));
        }
        
        
    }
    
}
