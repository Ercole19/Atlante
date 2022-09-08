package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.normal.SendReviewBean;
import com.example.athena.beans.normal.TutoringInformationBean;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.exceptions.TutorReviewException;
import com.example.athena.graphical_controller.normal_interface.PostInitialize;
import com.example.athena.use_case_controllers.ReviewTutorUseCaseController;
import com.example.athena.view.oracle_view.LabelView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.util.ArrayList;

public class OracleReviewGC implements PostInitialize {

    @FXML
    private Label tutorName;
    @FXML
    private Label tutoringSubject;
    @FXML
    private Label tutoringDay;
    @FXML
    private RadioButton oneStarButton;
    @FXML
    private RadioButton twoStarButton;
    @FXML
    private RadioButton threeStarButton;
    @FXML
    private RadioButton fourStarButton;
    @FXML
    private RadioButton fiveStarButton;

    private String code;

    public void setCode(String code)
    {
        this.code = code ;
    }

    public void postInitialize(ArrayList<Object> params) {
        code = (String) params.get(0) ;
        setCode(code) ;
        TutoringInformationBean bean = (TutoringInformationBean) params.get(1);
        setLabels(bean);
    }

    private void setLabels(TutoringInformationBean bean) {
        tutorName.setText(bean.getTutorsName()) ;
        tutoringSubject.setText(bean.getTutoringSubject()) ;
        tutoringDay.setText(bean.getTutoringDaysHour()) ;
    }

    public void clickOnSubmitReviewButton()
    {

        int reviewStars ;
        if(fiveStarButton.isSelected())
        {
            reviewStars = 5 ;
        }
        else if(fourStarButton.isSelected())
        {
            reviewStars = 4 ;
        }
        else if(threeStarButton.isSelected())
        {
            reviewStars = 3 ;
        }
        else if(twoStarButton.isSelected())
        {
            reviewStars = 2 ;
        }
        else if(oneStarButton.isSelected())
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

        LabelView view = new LabelView() ;
        
        try
        {
            controller.sendReview(reviewBean) ;
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Review submitted successfully!"));
        }catch (TutorReviewException e)
        {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in reviewing tutor"));
        }
    }
}
