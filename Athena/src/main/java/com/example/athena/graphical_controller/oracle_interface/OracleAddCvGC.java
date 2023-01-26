package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.TutorCvInfoBean;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.use_case_controllers.TutorPersonalPageUCC;
import com.example.athena.view.oracle_view.LabelView;

import java.io.File;

public class OracleAddCvGC  {

    public void addCv(String pathname) {
        LabelView view = new LabelView();
        TutorCvInfoBean bean = new TutorCvInfoBean() ;
        bean.setFile(new File(pathname)) ;
        TutorPersonalPageUCC controller = new TutorPersonalPageUCC();
        try {
            controller.uploadCV(bean);
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Cv added"));
        } catch (UserInfoException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in getting User information, details follow: " + e.getMessage()));
        }
    }

}
