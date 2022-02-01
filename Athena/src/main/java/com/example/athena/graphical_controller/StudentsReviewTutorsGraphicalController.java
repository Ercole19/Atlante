package com.example.athena.graphical_controller;

import com.example.athena.exceptions.TutorReviewException;
import com.example.athena.use_case_controllers.ReviewTutorUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static javafx.fxml.FXMLLoader.load;

public class StudentsReviewTutorsGraphicalController implements PostInitialize
{
    private Parent root;
    private Scene scene;
    private static final String REVIEW_SECTION_PROMPT = "#reviewSectionPrompt";
    private String code ;

    @FXML
    private TextField reviewCodeTextField ;

    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "tutorSearchPage.fxml");
    }

    public void clickOnSubmitButton(ActionEvent event) throws IOException
    {
        try
        {
            ReviewTutorUseCaseController reviewController = new ReviewTutorUseCaseController() ;

            String reviewCode = this.reviewCodeTextField.getText() ;

            TutoringInformationBean reviewInfo = reviewController.reviewTutor(new ReviewCodeBean(reviewCode)) ;

            SceneSwitcher switcher = new SceneSwitcher() ;
            root = switcher.preload( "tutorPersonalReview.fxml", new ArrayList<>(Collections.singleton(reviewCode)));

            Label tutorName = (Label) root.lookup("#tutorName") ;
            Label tutoringSubject = (Label) root.lookup("#tutoringSubject") ;
            Label tutoringDay = (Label) root.lookup("#tutoringDay") ;

            tutorName.setText(reviewInfo.getTutorsName()) ;
            tutoringSubject.setText(reviewInfo.getTutoringSubject()) ;
            tutoringDay.setText(reviewInfo.getTutoringDaysHour()) ;

            scene = ((Node) event.getSource()).getScene() ;
            SubScene reviewSubscene = (SubScene) scene.lookup(REVIEW_SECTION_PROMPT) ;
            reviewSubscene.setRoot(root) ;
        }catch(TutorReviewException exception)
        {
            root = load((new SceneSwitcher()).generateUrl("ErrorInReviewView.fxml")) ;
            scene = ((Node) event.getSource()).getScene() ;
            SubScene reviewSubscene = (SubScene) scene.lookup(REVIEW_SECTION_PROMPT) ;
            reviewSubscene.setRoot(root) ;
        }
    }

    public void clickOnSubmitReviewButton(ActionEvent event) throws IOException
    {
        scene = ((Node) event.getSource()).getScene() ;
        SubScene reviewSubscene = (SubScene) scene.lookup(REVIEW_SECTION_PROMPT) ;
        root = reviewSubscene.getRoot() ;

        int reviewStars ;
        RadioButton button = (RadioButton) root.lookup("#oneStarButton") ;
        String selectedButtonID = (String) button.getToggleGroup().getSelectedToggle().getProperties().get("fx:id") ;
        if(selectedButtonID.equals("#oneStarButton"))
        {
            reviewStars = 1 ;
        }
        else if(selectedButtonID.equals("#twoStarButton"))
        {
            reviewStars = 2 ;
        }
        else if(selectedButtonID.equals("#threeStarButton"))
        {
            reviewStars = 3 ;
        }
        else if(selectedButtonID.equals("#fourStarButton"))
        {
            reviewStars = 4 ;
        }
        else if(selectedButtonID.equals("#fiveStarButton"))
        {
            reviewStars = 5 ;
        }
        else
        {
            return ;
        }

        SendReviewBean reviewBean = new SendReviewBean(reviewStars, this.code) ;
        ReviewTutorUseCaseController controller = new ReviewTutorUseCaseController() ;

        try
        {
            controller.sendReview(reviewBean) ;
            reviewSubscene.setRoot(new AnchorPane()) ;
        }catch (TutorReviewException e)
        {
            root = load((new SceneSwitcher()).generateUrl("ErrorInReviewView.fxml")) ;
            reviewSubscene.setRoot(root) ;
        }
    }

    public void setCode(String code)
    {
        this.code = code ;
    }

    public void postInitialize(ArrayList<Object> params) {
        code = (String) params.get(0) ;
        setCode(code) ;
    }
}
