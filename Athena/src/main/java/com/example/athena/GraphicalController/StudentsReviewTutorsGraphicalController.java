package com.example.athena.GraphicalController;

import com.example.athena.Exceptions.TutorReviewException;
import com.example.athena.UseCaseControllers.ReviewTutorUseCaseController;
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
import java.util.Objects;

import static javafx.fxml.FXMLLoader.load;

public class StudentsReviewTutorsGraphicalController
{
    private Parent root;
    private Scene scene;

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

            root = load((new SceneSwitcher()).generateUrl("tutorPersonalReview.fxml")) ;

            Label tutorName = (Label) root.lookup("#tutorName") ;
            Label tutoringSubject = (Label) root.lookup("#tutoringSubject") ;
            Label tutoringDay = (Label) root.lookup("#tutoringDay") ;

            tutorName.setText(reviewInfo.getTutorsName()) ;
            tutoringSubject.setText(reviewInfo.getTutoringSubject()) ;
            tutoringDay.setText(reviewInfo.getTutoringDaysHour()) ;

            scene = ((Node) event.getSource()).getScene() ;
            SubScene reviewSubscene = (SubScene) scene.lookup("#reviewSectionPrompt") ;
            reviewSubscene.setRoot(root) ;
        }catch(TutorReviewException exception)
        {
            root = load((new SceneSwitcher()).generateUrl("ErrorInReviewView.fxml")) ;
            scene = ((Node) event.getSource()).getScene() ;
            SubScene reviewSubscene = (SubScene) scene.lookup("#reviewSectionPrompt") ;
            reviewSubscene.setRoot(root) ;
        }
    }

    public void clickOnSubmitReviewButton(ActionEvent event) throws IOException
    {
        scene = ((Node) event.getSource()).getScene() ;
        SubScene reviewSubscene = (SubScene) scene.lookup("#reviewSectionPrompt") ;
        root = reviewSubscene.getRoot() ;

        int reviewStars ;
        RadioButton button ;

        if((button = ((RadioButton) root.lookup("#oneStarButton"))).isSelected())
        {
            reviewStars = Integer.parseInt(button.getText()) ;
        }
        else if((button = ((RadioButton) root.lookup("#twoStarButton"))).isSelected())
        {
            reviewStars = Integer.parseInt(button.getText()) ;
        }
        else if((button = ((RadioButton) root.lookup("#threeStarButton"))).isSelected())
        {
            reviewStars = Integer.parseInt(button.getText()) ;
        }
        else if((button = ((RadioButton) root.lookup("#fourStarButton"))).isSelected())
        {
            reviewStars = Integer.parseInt(button.getText()) ;
        }
        else if((button = ((RadioButton) root.lookup("#fiveStarButton"))).isSelected())
        {
            reviewStars = Integer.parseInt(button.getText()) ;
        }
        else
        {
            return ;
        }

        SendReviewBean reviewBean = new SendReviewBean(reviewStars) ;
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

    public void onLogoutButtonClick(ActionEvent event) throws IOException {

        SceneSwitcher switcher = new SceneSwitcher() ;
        switcher.switcher(event, "LoginPage.fxml") ;
    }
}
