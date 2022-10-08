package com.example.athena.view.oracle_view;

import com.example.athena.beans.normal.UserBean;
import com.example.athena.entities.Tutor;
import com.example.athena.entities.TutorPersonalPageSubject;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.graphical_controller.oracle_interface.OracleGenerateReviewGC;
import com.example.athena.view.LabelBuilder;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

public class ChooseSubjectView {

    private final Parent root ;
    private ChoiceBox<String> choiceBox ;

    public ChooseSubjectView(OracleGenerateReviewGC controller) {
        List<String> courses ;
        try {
            UserBean userBean = new UserBean() ;
            userBean.setEmail(Tutor.getInstance().getEmail());
            courses = TutorPersonalPageSubject.getInstance().getTutorInfos(userBean).getTutorCourses() ;
        } catch (UserInfoException | CourseException e) {
            this.root = new LabelView().prepareParent("Error in accessing information about your courses :" + e.getMessage());
            return;
        }

        VBox box = new VBox() ;

        Label title = LabelBuilder.buildLabel("Choose the subject of the tutoring") ;
        this.choiceBox = new ChoiceBox<>() ;
        this.choiceBox.getItems().addAll(courses) ;

        Button submit = new Button("Generate review") ;
        submit.setOnAction(event -> controller.receiveSubject(this)) ;

        box.getChildren().addAll(title, choiceBox, submit) ;

        this.root = box ;
    }

    public Parent getRoot() {
        return this.root ;
    }

    public String getChoiceBoxSelection() {
        return this.choiceBox.getValue() ;
    }
}
