package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.UserBean;
import com.example.athena.entities.LoggedTutor;
import com.example.athena.entities.TutorPersonalPageSubject;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.view.oracle_view.LabelView;
import com.example.athena.view.oracle_view.OnChooseSubjectView;
import javafx.scene.control.ChoiceBox;

import java.util.List;

public class OnChooseSubjectGC {

    private final OracleGenerateReviewGC controller ;

    private final OnChooseSubjectView view ;

    public OnChooseSubjectGC(OracleGenerateReviewGC controller) {
        this.controller = controller ;
        this.view = new OnChooseSubjectView() ;
        setUpInterface() ;
        ParentSubject.getInstance().setCurrentParent(view.getRoot());
    }

    private void setUpInterface() {
        ChoiceBox<String> choiceBox = this.view.getChoiceBox() ;

        List<String> courses ;
        try {
            UserBean userBean = new UserBean() ;
            userBean.setEmail(LoggedTutor.getInstance().getEmail().getMail());
            courses = TutorPersonalPageSubject.getInstance().getTutorInfos(userBean).getTutorCourses() ;
        } catch (UserInfoException | CourseException e) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Error in accessing information about your courses :" + e.getMessage())) ;
            return;
        }

        choiceBox.getItems().addAll(courses) ;

        view.getSubmit().setOnAction(event -> onClickOnSubmit());
    }

    public void onClickOnSubmit() {
        this.controller.setSubject(view.getChoiceBox().getValue());
        controller.advance() ;
    }
}
