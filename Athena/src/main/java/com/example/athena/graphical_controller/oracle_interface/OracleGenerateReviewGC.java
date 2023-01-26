package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.ReviewInfoBean;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.TutorReviewException;
import com.example.athena.graphical_controller.oracle_interface.generate_review_states.GenerateReviewAbstractState;
import com.example.athena.graphical_controller.oracle_interface.generate_review_states.GenerateReviewSM;
import com.example.athena.graphical_controller.oracle_interface.generate_review_states.OnChooseSubjectState;
import com.example.athena.use_case_controllers.ReviewTutorUseCaseController;
import com.example.athena.view.oracle_view.OnChooseSubjectView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class OracleGenerateReviewGC {
    private final String student;
    private final String date;
    private final String startHour;
    private final String endHour;
    private String subject;

    private final GenerateReviewSM machine ;

    public OracleGenerateReviewGC(String student, String date, String startHour, String endHour) {
        this.student = student;
        this.date = date;
        this.endHour = endHour;
        this.startHour = startHour;
        this.machine = new GenerateReviewSM(this, new OnChooseSubjectState(this)) ;
    }

    public String generateReview() throws TutorReviewException {
        ReviewTutorUseCaseController controller = new ReviewTutorUseCaseController();
        ReviewInfoBean bean = new ReviewInfoBean();

        try {
            bean.setUsername(student) ;
            bean.setDay(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            bean.setStartTime(LocalTime.parse(startHour, DateTimeFormatter.ofPattern("HH:mm"))) ;
            bean.setEndTime(LocalTime.parse(endHour, DateTimeFormatter.ofPattern("HH:mm"))) ;
            bean.setSubject(subject) ;
            return controller.insertReview(bean).getReviewCode() ;
        }
        catch (DateTimeParseException | TutorReviewException | SendEmailException e) {
            throw new TutorReviewException("Error in generating code, details follow: " + e.getMessage());
        }
    }

    public void setSubject(String subject) {
        this.subject = subject ;
    }

    public void advance() {
        this.machine.goNext() ;
    }
}