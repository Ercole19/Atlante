package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.normal.TutorInfosBean;
import com.example.athena.beans.normal.UserBean;
import com.example.athena.beans.oracle.OracleReviewTutorSendUsernameBean;
import com.example.athena.entities.Tutor;
import com.example.athena.entities.TutorPersonalPageSubject;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.TutorReviewException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.use_case_controllers.ReviewTutorUseCaseController;
import com.example.athena.view.oracle_view.LabelView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class OracleGenerateReviewGC implements Initializable {

    @FXML
    TextField studentUsername;
    @FXML
    TextField startTime;
    @FXML
    TextField endTime;
    @FXML
    ChoiceBox<String> subjectChoiceBox;
    @FXML
    DatePicker dayDatePicker;
    private final LabelView view = new LabelView();


    public void confirmGeneration() {
        String username = studentUsername.getText() ;
        String subject ;
        try
        {
            subject = (subjectChoiceBox.getValue()) ;
        }
        catch (IllegalArgumentException | NullPointerException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Enter a subject for the review!"));
            return ;
        }

        LocalDate day = dayDatePicker.getValue() ;

        String start = startTime.getText();
        String end =   endTime.getText();
        OracleReviewTutorSendUsernameBean dataBean = null;
        ReviewTutorUseCaseController controller = new ReviewTutorUseCaseController() ;
        try {
            dataBean = new OracleReviewTutorSendUsernameBean(username, subject, day, start, end) ;
            String generatedCode = controller.generateReview(dataBean) ;
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("This is your code" + generatedCode));
        } catch (DateTimeParseException e) {
            LabelView labelView = new LabelView();
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent(e.getMessage()));
        }
        catch (TutorReviewException | SendEmailException e)
        {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in generating code, details follow: " + e.getMessage()));

        }
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
}