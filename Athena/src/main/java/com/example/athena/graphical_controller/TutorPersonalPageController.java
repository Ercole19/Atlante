package com.example.athena.graphical_controller;

import com.example.athena.entities.CourseDao;
import com.example.athena.entities.UserDao;
import com.example.athena.use_case_controllers.TutorPersonalPageUCC;
import com.example.athena.use_case_controllers.ViewTutorPageUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;


public class TutorPersonalPageController implements  PostInitialize , Initializable
{


    @FXML
    private AnchorPane rootPane ;
    @FXML
    private TextArea aboutme;
    @FXML
    private TextArea coursesArea ;
    @FXML
    private TextArea sessioninfos ;
    @FXML
    private TextArea contactnumbers ;
    @FXML
    private TextArea reviewsArea;
    @FXML
    private Label nometutor;
    @FXML
    private Label cognometutor;




    private File file ;

    private String email ;


    public void clickOnBackButtonTutor(ActionEvent event) throws IOException
    {
        switchScene(event , "MainPageTutor.fxml");
    }

    public void clickOnBackButton(ActionEvent event) throws IOException
    {

        switchScene(event, "tutorSearchPage.fxml");
    }




    public void switchScene(ActionEvent event , String fxml) throws IOException {
        SceneSwitcher switcher = new SceneSwitcher() ;
        switcher.switcher(event, fxml) ;
    }

    public void onCVButtonClick(ActionEvent event) throws IOException
    {
        SceneSwitcher switcher = new SceneSwitcher() ;
        ViewTutorPageUseCaseController tutorPage = new ViewTutorPageUseCaseController();
        tutorPage.getCV(this.email);
        String name = "tempCV.html" ;
        ArrayList<Object> params = new ArrayList<>() ;
        params.add(name) ;
        switcher.popup("tutorCVView.fxml " , "CV", params) ;
    }

    public void onConfirmButtonClick(ActionEvent event) throws IOException {
        TutorPersonalPageUCC controller = new TutorPersonalPageUCC() ;

        UserBean bean = new UserBean() ;
        TutorInfosBean infos = new TutorInfosBean();
        infos.setAboutMe(aboutme.getText());
        infos.setContactNumbers(contactnumbers.getText());
        infos.setSessionInfos(sessioninfos.getText());

        bean.setEmail(com.example.athena.entities.User.getUser().getEmail());
        String[] tutorInfos = controller.getTutorInfos(bean);
        boolean empty = true ;

        for (Object ob : tutorInfos) {
            if (ob != null) {
                empty = false ;
                break ;
            }
        }

        if (empty) {

           controller.setTutorInformation(infos);
        }
        else {
            controller.updateTutorInformation(infos);
        }

        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageTutor.fxml");
    }

    public void onCVButtonClicktutor() throws IOException
    {
        TutorPersonalPageUCC controller = new TutorPersonalPageUCC();

        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "HTML files", "html") ;
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            this.file = fc.getSelectedFile();
        }

        controller.uploadCV(file);
    }


    public void onaddcoursebuttoclick() throws IOException{
        SceneSwitcher switcher = new SceneSwitcher() ;
        switcher.popup("addcourse.fxml" , "Add course") ;
    }


    @Override
    public void postInitialize(ArrayList<Object> params)
    {
        UserBean bean = new UserBean();
        bean.setEmail((String) params.get(0));
        TutorPersonalPageUCC controller = new TutorPersonalPageUCC();

        String[] tutorName = controller.getTutorName(bean);
        Float tutorAvgReviews = controller.getTutorReviewsAvg(bean);
        String[] tutorInfos = controller.getTutorInfos(bean);
        List<String> tutorCourses = controller.getTutorCourses(bean);

        if (tutorInfos == null) {
            aboutme.appendText("");
            sessioninfos.appendText("");
            contactnumbers.appendText("");
            reviewsArea.appendText("No data");
        }
        else {
            aboutme.appendText(tutorInfos[0]);
            sessioninfos.appendText(tutorInfos[1]);
            contactnumbers.appendText(tutorInfos[2]);
            if (tutorAvgReviews==0.0){
                reviewsArea.appendText("No reviews");
            }
            else {
                reviewsArea.appendText(String.valueOf(tutorAvgReviews));
            }

        }

        for (int i = 0 ; i< tutorCourses.size() ; i++) {
            coursesArea.appendText(tutorCourses.get(i) + "\n" );
        }

        nometutor.setText(tutorName[0]);
        cognometutor.setText(tutorName[1]);


        if((boolean)params.get(1))
        {
            aboutme.setEditable(false) ;
            sessioninfos.setEditable(false) ;
            contactnumbers.setEditable(false) ;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {rootPane.getProperties().put("foo", this) ;}
}
