package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.OracleExamBean;
import com.example.athena.exceptions.ExamException;
import com.example.athena.use_case_controllers.ManageExamsUCC;
import com.example.athena.view.oracle_view.LabelView;

public class OracleAddExamsGC {
    public void addExam(String name, String cfus, String vote, String date) {
        LabelView view = new LabelView();
        OracleExamBean bean = new OracleExamBean();
        ManageExamsUCC controller = new ManageExamsUCC();
        try {
            int grade = Integer.parseInt(vote);
            int cfu = Integer.parseInt(cfus);
            if (!((grade < 18 || grade > 30) || (cfu < 2 || cfu > 18))) {
                bean.setExamGrade(vote);
                bean.setExamDate(date);
                bean.setExamCfus(cfus);
                bean.setExamName(name);
                controller.addExam(bean);
            }
        }
        catch (ExamException | NumberFormatException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in adding exam, details follow: " + e.getMessage()));
        }

    }
}
