package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.entities.PersonalTakenExams;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.ExamException;
import com.example.athena.view.oracle_view.LabelView;

public class ShowCfusCompletionGC {

    public String[] getCfusInfos() {
        LabelView view = new LabelView();
        String[] cfusInfos = new String[2];
        try {
            cfusInfos[0] = String.valueOf(PersonalTakenExams.getInstance().getGainedCfusNumber());
            cfusInfos[1] = String.valueOf(LoggedStudent.getInstance().getCurrentStudent().getMaxCfu());
        } catch (ExamException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in retrieving necessary information"));
        }
        return cfusInfos;
    }
}
