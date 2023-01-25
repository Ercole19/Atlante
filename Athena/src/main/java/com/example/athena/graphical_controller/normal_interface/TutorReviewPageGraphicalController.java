package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.ReviewInfoBean;
import com.example.athena.entities.*;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.TutorReviewException;
import com.example.athena.beans.TutorInfosBean;
import com.example.athena.beans.UserBean;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.use_case_controllers.ReviewTutorUseCaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class TutorReviewPageGraphicalController implements Initializable
{
    @FXML
    private Label starsNumber ;

    @FXML
    private Label reviewCode ;

    @FXML
    private Label resultMessage ;

    @FXML
    private TextField studentUsername ;

    @FXML
    private ChoiceBox<String> subjectChoiceBox ;

    @FXML
    private Spinner<Integer> startHourSpinner ;

    @FXML
    private Spinner<Integer> startMinuteSpinner ;

    @FXML
    private Spinner<Integer> endHourSpinner ;

    @FXML
    private Spinner<Integer> endMinuteSpinner ;

    @FXML
    private DatePicker dayDatePicker ;

    public void clickOnBackButton() throws IOException
    {
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        switcher.switcher("MainPageTutor.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        UserBean bean = new UserBean();
        bean.setEmail(Tutor.getInstance().getEmail());
        try {
            TutorInfosBean tutorInfosBean = TutorPersonalPageSubject.getInstance().getTutorInfos(bean);
            starsNumber.setText(String.valueOf(tutorInfosBean.getAvgReview())) ;
            for(String subject : tutorInfosBean.getTutorCourses())
            {
                subjectChoiceBox.getItems().add(subject) ;
            }
        } catch (CourseException | UserInfoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in retrieving infos from db, infos follow: " + e.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
        }

        prepareFactory(startHourSpinner, 0, 23) ;
        prepareFactory(startMinuteSpinner, 0, 59) ;
        prepareFactory(endHourSpinner, 0, 23) ;
        prepareFactory(endMinuteSpinner, 0, 59) ;
    }

    private void prepareFactory(Spinner<Integer> spinner, int rangeStart, int rangeEnd)
    {
        SpinnerValueFactory<Integer> minutesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(rangeStart, rangeEnd) ;
        minutesValueFactory.setWrapAround(true) ;
        minutesValueFactory.setConverter(new StringHoursConverter(rangeStart, rangeEnd)) ;
        minutesValueFactory.setValue(0) ;

        spinner.setValueFactory(minutesValueFactory) ;
    }

    public void generateReviewCode()
    {
        String username = studentUsername.getText() ;
        String subject ;
        try
        {
            subject = subjectChoiceBox.getValue() ;
        }
        catch (IllegalArgumentException | NullPointerException e)
        {
            resultMessage.setText("Enter a subject for the review!") ;
            return ;
        }

        LocalDate day = dayDatePicker.getValue() ;

        int startHour = startHourSpinner.getValue() ;
        int startMinute = startMinuteSpinner.getValue() ;
        int endHour = endHourSpinner.getValue() ;
        int endMinute = endMinuteSpinner.getValue() ;

        LocalTime startTime = LocalTime.of(startHour, startMinute) ;
        LocalTime endTime = LocalTime.of(endHour, endMinute) ;

        ReviewInfoBean dataBean = new ReviewInfoBean() ;

        dataBean.setUsername(username);
        dataBean.setDay(day) ;
        dataBean.setStartTime(startTime) ;
        dataBean.setEndTime(endTime) ;
        dataBean.setSubject(subject) ;

        ReviewTutorUseCaseController controller = new ReviewTutorUseCaseController() ;

        try
        {
            String generatedCode = controller.insertReview(dataBean) ;
            reviewCode.setText(generatedCode) ;
            resultMessage.setText("Here is your review code") ;
        }
        catch (TutorReviewException|SendEmailException e)
        {
            resultMessage.setText(e.getMessage()) ;
        }
    }
}
