package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.ReviewInfoBean;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.TutorReviewException;
import com.example.athena.graphical_controller.oracle_interface.generate_review_states.GenerateReviewAbstractState;
import com.example.athena.graphical_controller.oracle_interface.generate_review_states.OnChooseSubjectState;
import com.example.athena.use_case_controllers.ReviewTutorUseCaseController;
import com.example.athena.view.oracle_view.ChooseSubjectView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        ReviewInfoBean bean = new ReviewInfoBean();

        try {
            bean.setUsername(student) ;
            bean.setDay(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            bean.setStartTime(LocalTime.parse(startHour, DateTimeFormatter.ofPattern("hh.mm"))) ;
            bean.setEndTime(LocalTime.parse(endHour, DateTimeFormatter.ofPattern("hh:mm"))) ;
            bean.setSubject(subject) ;
            return controller.insertReview(bean);
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