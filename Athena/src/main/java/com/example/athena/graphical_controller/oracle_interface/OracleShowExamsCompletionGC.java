package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.entities.LoggedStudent;
import com.example.athena.entities.PersonalTakenExams;
import com.example.athena.exceptions.ExamException;
import com.example.athena.view.oracle_view.LabelView;

public class OracleShowExamsCompletionGC {
    public String[] getExamsInformation() {
        String[] examsInfos = new String[2];
        try {
            examsInfos[0] = String.valueOf(PersonalTakenExams.getInstance().getTakenExamsNumber());
            examsInfos[1] = String.valueOf(LoggedStudent.getInstance().getCurrentStudent().getMaxExams());
        } catch (ExamException e) {
            LabelView view = new LabelView();
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in retrieving infos, details follow: " + e.getMessage()));
        }
        return examsInfos ;
    }
}
