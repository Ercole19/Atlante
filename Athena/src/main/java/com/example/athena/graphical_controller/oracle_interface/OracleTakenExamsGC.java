package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.OutputExamBean;
import com.example.athena.entities.ExamsSubject;
import com.example.athena.exceptions.ExamException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.view.oracle_view.LabelView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OracleTakenExamsGC {
    public String getExams() {
        LabelView labelView = new LabelView();
        String allExams = "";
        ObservableList<OutputExamBean> totalExams = FXCollections.observableArrayList() ;
        try {
            totalExams = ExamsSubject.getInstance().getExams();
        } catch (ExamException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Error in retrieving informations, details follow: " + e.getMessage()));
        }
        for (OutputExamBean bean : totalExams) {
            allExams = allExams.concat(bean.getExamName() + "  " + bean.getExamDate() + "  " + bean.getExamGrade() + "  " + bean.getExamCfu() + "\n");
        }
        return allExams;
    }

}
