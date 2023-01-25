package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.OracleTutorCvInfoBean;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.use_case_controllers.TutorPersonalPageUCC;
import com.example.athena.view.oracle_view.LabelView;

public class OracleAddCvGC  {

    public void addCv(String pathname) {
        LabelView view = new LabelView();
        OracleTutorCvInfoBean bean = new OracleTutorCvInfoBean();
        bean.setFile(pathname);
        TutorPersonalPageUCC controller = new TutorPersonalPageUCC();
        try {
            controller.uploadCV(bean);
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Cv added"));
        } catch (UserInfoException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in getting User information, details follow: " + e.getMessage()));
        }
    }

}
