package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.SetMaxCfusOrExamsBean;
import com.example.athena.entities.ExamsOrCfusEnum;
import com.example.athena.exceptions.CareerStatusException;
import com.example.athena.exceptions.ExamException;
import com.example.athena.exceptions.StudentInfoException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.use_case_controllers.SetMaxCfusOrExamsUCC;
import com.example.athena.view.oracle_view.LabelView;

public class OracleSetMaxCfusGC {

    public void setMaxCfus(String maxCfus) {
        LabelView view =  new LabelView();
        try {
            SetMaxCfusOrExamsBean bean = new SetMaxCfusOrExamsBean();
            bean.setNewMax(maxCfus);
            bean.setType(ExamsOrCfusEnum.SET_MAX_CFUS);
            SetMaxCfusOrExamsUCC controller = new SetMaxCfusOrExamsUCC();
            controller.setInfos(bean);
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("max cfus changed"));
        } catch (CareerStatusException | ExamException |  StudentInfoException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in setting max cfus value, details follow: " + e.getMessage()));
        }
    }

}
