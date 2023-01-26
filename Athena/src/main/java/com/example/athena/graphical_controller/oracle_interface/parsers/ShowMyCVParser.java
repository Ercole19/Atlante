package com.example.athena.graphical_controller.oracle_interface.parsers;


import com.example.athena.beans.UserBean;
import com.example.athena.entities.LoggedTutor;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.exceptions.NoCvException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.use_case_controllers.ViewTutorPageUseCaseController;
import com.example.athena.view.oracle_view.LabelView;

import java.util.ArrayList;

public class ShowMyCVParser {
    private final LabelView labelView = new LabelView();
    public void parseInfos() {
        try{
            if (LoggedTutor.getInstance().getEmail() != null) {
                    ViewTutorPageUseCaseController tutorPage = new ViewTutorPageUseCaseController();
                    tutorPage.getCV();
                    String name = "tempCV.html" ;
                    ArrayList<Object> params = new ArrayList<>() ;
                    params.add(name) ;
                    SceneSwitcher.getInstance().popup("tutorCVView.fxml " , "CV", params) ;
            }
            else {
                ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("You must login before writing any command"));
            }
        } catch (UserInfoException | NoCvException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Error in retrieving CV, details follow: " + e.getMessage()));
        }catch (LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Only tutors can see their cvs"));
        }
    }
}