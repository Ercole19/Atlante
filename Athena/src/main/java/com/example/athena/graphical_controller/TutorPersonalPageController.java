package com.example.athena.graphical_controller;

import com.example.athena.engineering_classes.observer_pattern.AbstractObserver;
import com.example.athena.entities.Tutor;
import com.example.athena.entities.TutorPersonalPageSubject;
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


public class TutorPersonalPageController implements  PostInitialize , Initializable, AbstractObserver
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

    private String email ;


    public void clickOnBackButtonTutor(ActionEvent event) throws IOException
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "MainPageTutor.fxml") ;
        TutorPersonalPageSubject.getInstance().detachObserver(this);
    }

    public void clickOnBackButton(ActionEvent event) throws IOException
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "tutorSearchPage.fxml") ;
        TutorPersonalPageSubject.getInstance().resetEntity();
        TutorPersonalPageSubject.getInstance().detachObserver(this);

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

    public void onConfirmButtonClick() throws IOException {
        TutorPersonalPageUCC controller = new TutorPersonalPageUCC() ;

        UserBean bean = new UserBean() ;
        TutorInfosBean infos = new TutorInfosBean();
        infos.setAboutMe(aboutMe.getText());
        infos.setContactNumbers(contactNumbers.getText());
        infos.setSessionInfos(sessionInfos.getText());

        bean.setEmail(Tutor.getInstance().getEmail());
        //List<String> tutorInfos = controller.getTutorInfos(bean);
        controller.setTutorInformation(infos);
        /*
        if (tutorInfos.isEmpty()) {
            controller.setTutorInformation(infos);
        }
        else {
            controller.updateTutorInformation(infos);
        }
        */

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


    public void onManageCoursesBtnClick() throws IOException {
        switcher.popup("addcourse.fxml" , "Manage courses") ;
    }

    @Override
    public void postInitialize(ArrayList<Object> params)
    {
        this.email = (String) params.get(0);

        if((boolean)params.get(1))
        {
            aboutMe.setEditable(false) ;
            sessionInfos.setEditable(false) ;
            contactNumbers.setEditable(false) ;
        }
        TutorPersonalPageSubject.getInstance().attachObserver(this);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        rootPane.getProperties().put("foo", this) ;
    }

    @Override
    public void update() {
        UserBean bean = new UserBean();
        bean.setEmail(this.email);
        TutorInfosBean tutorInfosBean = TutorPersonalPageSubject.getInstance().getTutorInfos(bean);

        aboutMe.setText(tutorInfosBean.getAboutMe());
        sessionInfos.setText(tutorInfosBean.getSessionInfos());
        contactNumbers.setText(tutorInfosBean.getContactNumbers());
        if (tutorInfosBean.getAvgReview() == 0.0){
            reviewAverage.setText("No reviews");
        }
        else {
            reviewAverage.setText(String.valueOf(tutorInfosBean.getAvgReview()));
        }

        coursesArea.clear();
        for (String course : tutorInfosBean.getTutorCourses()) {
            coursesArea.appendText(course + "\n" );
        }

        this.tutorName.setText(tutorInfosBean.getName());
        tutorSurname.setText(tutorInfosBean.getSurname());
    }
}
