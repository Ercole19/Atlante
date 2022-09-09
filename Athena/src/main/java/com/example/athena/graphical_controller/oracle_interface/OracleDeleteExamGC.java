package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.oracle.OracleExamBean;
import com.example.athena.exceptions.ExamException;
import com.example.athena.use_case_controllers.ManageExamsUCC;
import com.example.athena.view.oracle_view.LabelView;

public class OracleDeleteExamGC {
    public void deleteExam(String examName, String examDate, String grade, String cfus) {
        OracleExamBean bean =  new OracleExamBean();
        ManageExamsUCC manageExamsUCC = new ManageExamsUCC();
        try {
            bean.setExamDate(examDate);
            bean.setExamName(examName);
            bean.setExamCfus(cfus);
            bean.setExamGrade(grade);
            manageExamsUCC.deleteExams(bean);
        } catch (ExamException e) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent(e.getMessage()));
        }
    }
}
