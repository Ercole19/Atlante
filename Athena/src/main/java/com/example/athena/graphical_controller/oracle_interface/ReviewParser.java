package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.normal.ReviewCodeBean;
import com.example.athena.beans.normal.TutoringInformationBean;
import com.example.athena.entities.Student;
import com.example.athena.exceptions.LoggedUserException;
import com.example.athena.exceptions.TutorReviewException;
import com.example.athena.graphical_controller.normal_interface.SceneSwitcher;
import com.example.athena.graphical_controller.normal_interface.StudentsReviewTutorsGraphicalController;
import com.example.athena.use_case_controllers.ReviewTutorUseCaseController;
import com.example.athena.view.oracle_view.LabelView;

import java.util.ArrayList;
import java.util.List;

public class ReviewParser {

    public void parseReview(List<String> commandToken) {
        LabelView view = new LabelView();
        if (commandToken.size()  == 0) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("insert code after review command"));
        }
        try{
            if (Student.getInstance().getEmail() != null){
                List<Object> params = new ArrayList<>() ;
                params.add(commandToken.get(0)) ;
                ReviewTutorUseCaseController reviewController = new ReviewTutorUseCaseController() ;
                ReviewCodeBean rc = new ReviewCodeBean();
                rc.setReviewCode(commandToken.get(0));
                TutoringInformationBean reviewInfo = reviewController.reviewTutor(rc) ;
                params.add(reviewInfo);
                ParentSubject.getInstance().setCurrentParent(SceneSwitcher.getInstance().preload("OracleReview.fxml", params));
            }
            else{
                ParentSubject.getInstance().setCurrentParent(view.prepareParent("You need to login before writing commands"));
            }
        } catch (LoggedUserException | TutorReviewException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("only students can review tutor"));
        }
    }

}
