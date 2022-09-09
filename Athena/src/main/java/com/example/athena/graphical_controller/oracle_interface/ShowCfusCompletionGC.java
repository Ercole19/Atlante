package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.entities.ExamsSubject;
import com.example.athena.exceptions.ExamException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.view.oracle_view.LabelView;

public class ShowCfusCompletionGC {

    public String[] getCfusInfos() {
        LabelView view = new LabelView();
        String[] cfusInfos = new String[2];
        try {
            cfusInfos[0] = String.valueOf(ExamsSubject.getInstance().getGainedCfusNumber());
            cfusInfos[1] = String.valueOf(ExamsSubject.getInstance().getTotalCfusNumber());
        } catch (ExamException | UserInfoException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in retrieving necessary information"));
        }
        return cfusInfos;
    }
}
