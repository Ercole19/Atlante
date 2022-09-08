package com.example.athena.graphical_controller.oracle_interface;


import com.example.athena.beans.normal.UserBean;
import com.example.athena.entities.Tutor;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import com.example.athena.use_case_controllers.ViewTutorPageUseCaseController;
import com.example.athena.view.oracle_view.LabelView;

import java.util.ArrayList;

public class ShowMyCVParser {
    private LabelView labelView = new LabelView();
    public void parseInfos() {
        try{
            if (Tutor.getInstance().getEmail() != null) {
                try {
                    ViewTutorPageUseCaseController tutorPage = new ViewTutorPageUseCaseController();
                    UserBean bean = new UserBean() ;
                    bean.setEmail(Tutor.getInstance().getEmail());
                    tutorPage.getCV(bean);
                    String name = "tempCV.html" ;
                    ArrayList<Object> params = new ArrayList<>() ;
                    params.add(name) ;
                    SceneSwitcher.getInstance().popup("tutorCVView.fxml " , "CV", params) ;
                } catch (UserInfoException e) {
                    ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Error in retrieving CV"));
                }
            }
            else {
                ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("You must login before writing any command"));
            }
        }catch (
                LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Only tutors can see their cvs"));
        }
    }
}