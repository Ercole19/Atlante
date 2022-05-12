package com.example.athena.graphical_controller;

import com.example.athena.use_case_controllers.TutorPersonalPageUCC;
import com.example.athena.use_case_controllers.ViewTutorPageUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private TextArea aboutMe;
    @FXML
    private TextArea coursesArea ;
    @FXML
    private TextArea sessionInfos;
    @FXML
    private TextArea contactNumbers;
    @FXML
    private Label tutorName;
    @FXML
    private Label tutorSurname;
    @FXML
    private Text reviewAverage;

    private final SceneSwitcher switcher = new SceneSwitcher();
    private Stage stage;

    private File file ;

    private String email  = Tutor.getInstance().getEmail();


    public void clickOnBackButtonTutor(ActionEvent event) throws IOException
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "MainPageTutor.fxml") ;
    }

    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "tutorSearchPage.fxml") ;
    }

    public void onCVButtonClick()
    {
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
        infos.setAboutMe(aboutMe.getText());
        infos.setContactNumbers(contactNumbers.getText());
        infos.setSessionInfos(sessionInfos.getText());

        bean.setEmail(com.example.athena.entities.Tutor.getInstance().getEmail());
        List<String> tutorInfos = controller.getTutorInfos(bean);

        if (tutorInfos.isEmpty()) {
            controller.setTutorInformation(infos);
        }
        else {
            controller.updateTutorInformation(infos);
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "MainPageTutor.fxml");
    }

    public void onCVButtonClickTutor() throws IOException
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


    public void onAddCourseButtonClick() throws IOException{
        switcher.popup("addcourse.fxml" , "Add course") ;
    }


    public void populatePage(UserBean bean) {

        TutorPersonalPageUCC controller = new TutorPersonalPageUCC() ;

        String[] tutorName = controller.getTutorName(bean);
        Float tutorAvgReviews = controller.getTutorReviewsAvg(bean);
        List<String> tutorInfos = controller.getTutorInfos(bean);
        List<String> tutorCourses = controller.getTutorCourses(bean);

        aboutMe.setText(tutorInfos.get(0));
        sessionInfos.setText(tutorInfos.get(1));
        contactNumbers.setText(tutorInfos.get(2));
        if (tutorAvgReviews==0.0){
            reviewAverage.setText("No reviews");
        }
        else {
            reviewAverage.setText(String.valueOf(tutorAvgReviews));
        }

        for (String course : tutorCourses) {
            coursesArea.appendText(course + "\n" );
        }

        this.tutorName.setText(tutorName[0]);
        tutorSurname.setText(tutorName[1]);
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
            aboutMe.setEditable(false) ;
            sessionInfos.setEditable(false) ;
            contactNumbers.setEditable(false) ;
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
