package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.ReviewTutorSendUsernameBean;
import com.example.athena.beans.normal.TutorInfosBean;
import com.example.athena.beans.normal.UserBean;
import com.example.athena.beans.oracle.OracleReviewTutorSendUsernameBean;
import com.example.athena.entities.Tutor;
import com.example.athena.entities.TutorPersonalPageSubject;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.TutorReviewException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.graphical_controller.oracle_interface.generate_review_states.GenerateReviewAbstractState;
import com.example.athena.graphical_controller.oracle_interface.generate_review_states.OnChooseSubjectState;
import com.example.athena.graphical_controller.oracle_interface.sell_book_states.SellBookAbstractState;
import com.example.athena.use_case_controllers.ReviewTutorUseCaseController;
import com.example.athena.view.oracle_view.LabelView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class OracleGenerateReviewGC {
    private String student;
    private String date;
    private String startHour;
    private String endHour;
    private GenerateReviewAbstractState state;
    private String subject;

    public void confirmGeneration(String student, String date, String startHour, String endHour) {
        this.student = student;
        this.date = date;
        this.endHour = endHour;
        this.startHour = startHour;
        this.state = new OnChooseSubjectState(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserBean bean = new UserBean();
        bean.setEmail(Tutor.getInstance().getEmail());
        try {
            TutorInfosBean tutorInfosBean = TutorPersonalPageSubject.getInstance().getTutorInfos(bean);
            for(String subject : tutorInfosBean.getTutorCourses())
            {
                subjectChoiceBox.getItems().add(subject) ;
            }
        } catch (CourseException | UserInfoException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in retrieving infos"));
        }
    }

    public void receiveSubject(ChooseSubjectView view) {
        this.subject = view.getChoiceBoxSelection() ;
        this.goNext() ;
    }

    public void setState(GenerateReviewAbstractState nextState) {
        this.state = nextState ;
    }

    public void goNext() {
        this.state.goNext(this) ;
    }
}