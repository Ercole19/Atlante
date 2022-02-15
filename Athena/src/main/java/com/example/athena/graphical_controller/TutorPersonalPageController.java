package com.example.athena.graphical_controller;

import com.example.athena.entities.CourseDao;
import com.example.athena.entities.User;
import com.example.athena.entities.UserDao;
import com.example.athena.use_case_controllers.TutorPersonalPageUCC;
import com.example.athena.use_case_controllers.ViewTutorPageUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
    @FXML
    private Text reviewAverage;




    private File file ;

    private String email  = User.getUser().getEmail();


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
        List<String> tutorInfos = controller.getTutorInfos(bean);

        if (tutorInfos.isEmpty()) {
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




    public void populatePage(UserBean bean) {

        TutorPersonalPageUCC controller = new TutorPersonalPageUCC() ;

        String[] tutorName = controller.getTutorName(bean);
        Float tutorAvgReviews = controller.getTutorReviewsAvg(bean);
        List<String> tutorInfos = controller.getTutorInfos(bean);
        List<String> tutorCourses = controller.getTutorCourses(bean);

        if (tutorInfos.isEmpty()) {
            aboutme.setText("No data");
            sessioninfos.setText("No data");
            contactnumbers.setText("No data");
            reviewAverage.setText("No data");
        }
        else {
            aboutme.setText(tutorInfos.get(0));
            sessioninfos.setText(tutorInfos.get(1));
            contactnumbers.setText(tutorInfos.get(2));
            if (tutorAvgReviews==0.0){
                reviewAverage.setText("No reviews");
            }
            else {
                reviewAverage.setText(String.valueOf(tutorAvgReviews));
            }

        }

        for (String course : tutorCourses) {
            coursesArea.appendText(course + "\n" );
        }

        nometutor.setText(tutorName[0]);
        cognometutor.setText(tutorName[1]);

    }





    @Override
    public void postInitialize(ArrayList<Object> params)
    {
        UserBean bean = new UserBean();
        this.email = (String) params.get(0);
        bean.setEmail((String) params.get(0));
        populatePage(bean);

        if((boolean)params.get(1))
        {
            aboutme.setEditable(false) ;
            sessioninfos.setEditable(false) ;
            contactnumbers.setEditable(false) ;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        rootPane.getProperties().put("foo", this) ;
        UserBean bean = new UserBean() ;
        bean.setEmail(this.email);
        populatePage(bean);
    }
}
