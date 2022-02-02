package com.example.athena.graphical_controller;

import com.example.athena.entities.CourseDao;
import com.example.athena.entities.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load ;

public class TutorPersonalPageController implements  PostInitialize , Initializable
{

    private Parent root ;
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
    private UserDao user ;



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
        initScene("tutorCVView.fxml " , "CV");
    }

    public void onConfirmButtonClick(ActionEvent event) throws IOException {
        user = new UserDao();
        String[] infos = user.filltutorinfos();
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

    public void onCVButtonClicktutor(ActionEvent event) throws IOException
    {
        AnchorPane anchorPane = new AnchorPane() ;
        anchorPane.setPrefSize(600, 800) ;
        Label cv = new Label("Lorem Ipsum Dolor Sit Amet") ;
        anchorPane.getChildren().add(cv) ;
        Scene cvscene = new Scene(root) ;
        Stage tempStage = new Stage() ;
        tempStage.setScene(cvscene) ;
        tempStage.initModality(Modality.APPLICATION_MODAL) ;
        tempStage.setResizable(false) ;
        tempStage.setTitle("CV") ;
        tempStage.showAndWait() ;
    }



    public void onaddcoursebuttoclick() throws IOException{
        initScene("addcourse.fxml" , "Add course");
    }



    public void initScene(String fxml , String windowText) throws IOException
    {

        FXMLLoader loader = new FXMLLoader();
        SceneSwitcher switcher = new SceneSwitcher() ;
        loader.setLocation(switcher.generateUrl(fxml));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        Scene scene = new Scene(loader.load());
        stage.setTitle(windowText);
        stage.setScene(scene);
        stage.showAndWait();

    }


    @Override
    public void postInitialize(ArrayList<Object> params)
    {


        user = new UserDao() ;
        CourseDao corso = new CourseDao() ;


        String[] infos = user.filltutorinfos();
        List<String> courses = corso.fillCourses() ;

        if (infos == null) {
            aboutme.setText("");
            sessioninfos.setText("");
            contactnumbers.setText("");
        }
        else {
            aboutme.setText(infos[0]);
            sessioninfos.setText(infos[1]);
            contactnumbers.setText(infos[2]);
        }

        for (int i = 0 ; i< courses.size() ; i++) {
            coursesArea.appendText(courses.get(i) + "\n" );
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rootPane.getProperties().put("foo", this) ;
    }
}
