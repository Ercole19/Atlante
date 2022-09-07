package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.ReviewCodeBean;
import com.example.athena.beans.SendReviewBean;
import com.example.athena.beans.TutoringInformationBean;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.exceptions.TutorReviewException;
import com.example.athena.use_case_controllers.ReviewTutorUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private SceneSwitcher switcher = SceneSwitcher.getInstance();

    public void clickOnBackButton() throws IOException
    {
        switcher.switcher("tutorSearchPage.fxml");
    }

    public void clickOnSubmitButton() throws IOException
    {
        try
        {
            ReviewTutorUseCaseController reviewController = new ReviewTutorUseCaseController() ;

            String reviewCode = this.reviewCodeTextField.getText() ;

            ReviewCodeBean rc = new ReviewCodeBean();
            rc.setReviewCode(reviewCode);
            TutoringInformationBean reviewInfo = reviewController.reviewTutor(rc) ;

            root = switcher.preload( "tutorPersonalReview.fxml", new ArrayList<>(Collections.singleton(reviewCode)));

            Label tutorName = (Label) root.lookup("#tutorName") ;
            Label tutoringSubject = (Label) root.lookup("#tutoringSubject") ;
            Label tutoringDay = (Label) root.lookup("#tutoringDay") ;

            tutorName.setText(reviewInfo.getTutorsName()) ;
            tutoringSubject.setText(reviewInfo.getTutoringSubject()) ;
            tutoringDay.setText(reviewInfo.getTutoringDaysHour()) ;

            scene = switcher.getTopStage().getScene();
            SubScene reviewSubscene = (SubScene) scene.lookup(REVIEW_SECTION_PROMPT) ;
            reviewSubscene.setRoot(root) ;
        }catch(TutorReviewException exception)
        {
            root = switcher.preload("ErrorInReviewView.fxml") ;
            scene = switcher.getTopStage().getScene();
            SubScene reviewSubscene = (SubScene) scene.lookup(REVIEW_SECTION_PROMPT) ;
            reviewSubscene.setRoot(root) ;
        }
    }

    public void clickOnSubmitReviewButton(ActionEvent event)
    {
        scene = ((Node) event.getSource()).getScene() ;
        SubScene reviewSubscene = (SubScene) scene.lookup(REVIEW_SECTION_PROMPT) ;
        root = reviewSubscene.getRoot() ;

        int reviewStars ;
        if(((RadioButton) root.lookup("#fiveStarButton")).isSelected())
        {
            reviewStars = 5 ;
        }
        else if((((RadioButton) root.lookup("#fourStarButton")).isSelected()))
        {
            reviewStars = 4 ;
        }
        else if(((RadioButton) root.lookup("#threeStarButton")).isSelected())
        {
            reviewStars = 3 ;
        }
        else if(((RadioButton) root.lookup("#twoStarButton")).isSelected())
        {
            reviewStars = 2 ;
        }
        else if(((RadioButton) root.lookup("#oneStarButton")).isSelected())
        {
            reviewStars = 1 ;
        }
        else
        {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "You must pick a review!");
            alert.showAndWait();
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
            root = switcher.preload("ErrorInReviewView.fxml") ;
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
