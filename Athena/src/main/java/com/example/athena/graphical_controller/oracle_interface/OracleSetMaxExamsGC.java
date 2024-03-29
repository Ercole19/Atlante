package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.normal.SetMaxCfusOrExamsBean;
import com.example.athena.entities.ExamsOrCfusEnum;
import com.example.athena.exceptions.CareerStatusException;
import com.example.athena.use_case_controllers.SetMaxCfusOrExamsUCC;
import com.example.athena.view.oracle_view.LabelView;

public class OracleSetMaxExamsGC {
    
    public void setMaxExams(String maxExams){
        LabelView view =  new LabelView();
        try {
            SetMaxCfusOrExamsBean bean = new SetMaxCfusOrExamsBean();
            bean.setNewMax(maxExams);
            bean.setType(ExamsOrCfusEnum.SET_MAX_EXAMS);
            SetMaxCfusOrExamsUCC controller = new SetMaxCfusOrExamsUCC();
            controller.setInfos(bean);
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("max exams value changed"));
        } catch (CareerStatusException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in setting max exams value, details follow: " + e.getMessage()));
        }
    }
}
