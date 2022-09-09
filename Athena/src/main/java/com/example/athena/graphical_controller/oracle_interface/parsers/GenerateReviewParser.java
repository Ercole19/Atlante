package com.example.athena.graphical_controller.oracle_interface.parsers;

import com.example.athena.entities.Tutor;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.view.oracle_view.LabelView;

public class GenerateReviewParser {

    private LabelView view = new LabelView();
    public void parseGenerateReview(){
        try{
            if (Tutor.getInstance().getEmail() != null){
                SceneSwitcher switcher = SceneSwitcher.getInstance();
                ParentSubject.getInstance().setCurrentParent(switcher.preload("OracleReviewPage.fxml"));
            }
            else{
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("You must login/signup before writing any command"));
            }
        } catch (LoggedUserException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("only tutors can generate review codes"));
        }

    }
}
