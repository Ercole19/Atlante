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
import com.example.athena.view.oracle_view.ChooseSubjectView;
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

    public String generateReview() throws TutorReviewException {
        ReviewTutorUseCaseController controller = new ReviewTutorUseCaseController();
        OracleReviewTutorSendUsernameBean bean = new OracleReviewTutorSendUsernameBean(this.student, this.subject, this.date, this.startHour, this.endHour);
        try {
            return controller.generateReview(bean);
        }
        catch (DateTimeParseException | TutorReviewException | SendEmailException e) {
            throw new TutorReviewException("Error in generating code, details follow: " + e.getMessage());
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