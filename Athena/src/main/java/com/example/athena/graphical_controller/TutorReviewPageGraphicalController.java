package com.example.athena.graphical_controller;

import com.example.athena.entities.StringHoursConverter;
import com.example.athena.entities.SubjectLabels;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.exceptions.TutorReviewException;
import com.example.athena.use_case_controllers.ReviewTutorUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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

    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        switcher.switcher(stage, "MainPageTutor.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        starsNumber.setText("5*") ;

        for(SubjectLabels subject : SubjectLabels.values())
        {
            subjectChoiceBox.getItems().add(subject.toString()) ;
        }

        prepareFactory(startHourSpinner, 0, 23) ;
        prepareFactory(startMinuteSpinner, 0, 59) ;
        prepareFactory(endHourSpinner, 0, 23) ;
        prepareFactory(endMinuteSpinner, 0, 59) ;
    }

    private void prepareFactory(Spinner<Integer> spinner, int rangeStart, int rangeEnd)
    {
        SpinnerValueFactory<Integer> minutesValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(rangeStart, rangeEnd) ;
        minutesValueFactory.setWrapAround(true) ;
        minutesValueFactory.setConverter(new StringHoursConverter(rangeStart, rangeEnd)) ;
        minutesValueFactory.setValue(0) ;

        spinner.setValueFactory(minutesValueFactory) ;
    }

    public void generateReviewCode()
    {
        String username = studentUsername.getText() ;
        SubjectLabels subject ;
        try
        {
            subject = SubjectLabels.valueOf(subjectChoiceBox.getValue()) ;
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

        ReviewTutorSendUsernameBean dataBean = new ReviewTutorSendUsernameBean(
                username, subject, day, startHour, startMinute, endHour, endMinute) ;

        ReviewTutorUseCaseController controller = new ReviewTutorUseCaseController() ;

        try
        {
            String generatedCode = controller.generateReview(dataBean) ;
            reviewCode.setText(generatedCode) ;
            resultMessage.setText("Here is your review code") ;
        }
        catch (TutorReviewException|SendEmailException e)
        {
            resultMessage.setText(e.getMessage()) ;
        }
    }
}
