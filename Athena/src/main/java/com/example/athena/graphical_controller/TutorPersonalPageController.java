package com.example.athena.graphical_controller;

import com.example.athena.entities.CourseDao;
import com.example.athena.entities.UserDao;
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


    private UserDao user ;

    private File file ;




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
        switcher.popup("tutorCVView.fxml " , "CV") ;
    }

    public void onConfirmButtonClick(ActionEvent event) throws IOException {
        user = new UserDao();
        String[] infos = user.filltutorinfos(com.example.athena.entities.User.getUser().getEmail());
        boolean empty = true ;
        for (Object ob : infos) {
            if (ob != null) {
                empty = false ;
                break ;
            }
        }

        if (empty) {

            user.settutorinfos(aboutme.getText(),  sessioninfos.getText(), contactnumbers.getText());
        }
        else {
            user.updatetutorinfos(aboutme.getText(),  sessioninfos.getText(), contactnumbers.getText());

        }

        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageTutor.fxml");
    }

    public void onCVButtonClicktutor() throws IOException
    {
        AnchorPane anchorPane = new AnchorPane() ;
        anchorPane.setPrefSize(600, 800) ;
        Label cv = new Label("Lorem Ipsum Dolor Sit Amet") ;
        anchorPane.getChildren().add(cv) ;
        Scene cvscene = new Scene(anchorPane) ;
        Stage tempStage = new Stage() ;
        tempStage.setScene(cvscene) ;
        tempStage.initModality(Modality.APPLICATION_MODAL) ;
        tempStage.setResizable(false) ;
        tempStage.setTitle("CV") ;
        tempStage.showAndWait() ;
    }


    public void onaddcoursebuttoclick() throws IOException{
        SceneSwitcher switcher = new SceneSwitcher() ;
        switcher.popup("addcourse.fxml" , "Add course") ;
    }


    @Override
    public void postInitialize(ArrayList<Object> params)
    {


        user = new UserDao() ;
        CourseDao corso = new CourseDao() ;

        String[] informazioni = user.getName((String) params.get(0));
        Float avg = user.getAvg((String) params.get(0));


        String[] infos = user.filltutorinfos((String) params.get(0));

        List<String> courses = corso.fillCourses((String) params.get(0)) ;

        if (infos == null) {
            aboutme.appendText("");
            sessioninfos.appendText("");
            contactnumbers.appendText("");
            reviewsArea.appendText("No data");
        }
        else {
            aboutme.appendText(infos[0]);
            sessioninfos.appendText(infos[1]);
            contactnumbers.appendText(infos[2]);
            if (avg==0.0){
                reviewsArea.appendText("No reviews");
            }
            else {
                reviewsArea.appendText(String.valueOf(avg));
            }

        }

        for (int i = 0 ; i< courses.size() ; i++) {
            coursesArea.appendText(courses.get(i) + "\n" );
        }

        nometutor.setText(informazioni[0]);
        cognometutor.setText(informazioni[1]);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {rootPane.getProperties().put("foo", this) ;}
}
