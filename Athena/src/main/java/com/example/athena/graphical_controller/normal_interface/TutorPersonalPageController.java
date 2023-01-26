package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.TutorCvInfoBean;
import com.example.athena.beans.TutorInfosBean;
import com.example.athena.beans.UserBean;
import com.example.athena.engineering_classes.observer_pattern.AbstractObserver;
import com.example.athena.entities.TutorPersonalPageSubject;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.NoCvException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.use_case_controllers.TutorPersonalPageUCC;
import com.example.athena.use_case_controllers.ViewTutorPageUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class TutorPersonalPageController implements PostInitialize, Initializable, AbstractObserver
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

    private final SceneSwitcher switcher = SceneSwitcher.getInstance();

    private String email ;


    public void clickOnBackButtonTutor(ActionEvent event)
    {
        switcher.switcher("MainPageTutor.fxml") ;
        TutorPersonalPageSubject.getInstance().detachObserver(this);
    }

    public void clickOnBackButton(ActionEvent event)
    {
        switcher.switcher("tutorSearchPage.fxml") ;
        new ViewTutorPageUseCaseController().exitPage() ;
        TutorPersonalPageSubject.getInstance().detachObserver(this);

    }

    public void onCVButtonClick()
    {
        ViewTutorPageUseCaseController tutorPage = new ViewTutorPageUseCaseController();
        try {
            tutorPage.getCV();
        } catch (UserInfoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in retrieving cv from db, try restarting application", ButtonType.CLOSE);
            alert.showAndWait();
        }
        catch (NoCvException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
            return;
        }
        String name = "tempCV.html" ;
        ArrayList<Object> params = new ArrayList<>() ;
        params.add(name) ;
        switcher.popup("tutorCVView.fxml " , "CV", params) ;
    }

    public void onConfirmButtonClick() {
        TutorPersonalPageUCC controller = new TutorPersonalPageUCC() ;

        TutorInfosBean infos = new TutorInfosBean();
        infos.setAboutMe(aboutMe.getText());
        infos.setContactNumbers(contactNumbers.getText());
        infos.setSessionInfos(sessionInfos.getText());

        try {
            controller.updateTutorInformation(infos);
        } catch (UserInfoException | CourseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in updating, try restarting application", ButtonType.CLOSE);
            alert.showAndWait();
        }

    }

    public void onCVButtonClickTutor()
    {
        TutorPersonalPageUCC controller = new TutorPersonalPageUCC();
        TutorCvInfoBean bean = new TutorCvInfoBean();

        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "HTML files", "html") ;
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            bean.setFile(file);
            try {
                controller.uploadCV(bean);
            } catch (UserInfoException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error in uploading cv, try restarting application", ButtonType.CLOSE);
                alert.showAndWait();
            }
        }
    }


    public void onManageCoursesBtnClick() {
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
        try {
            TutorInfosBean tutorInfosBean = TutorPersonalPageSubject.getInstance().getTutorInfos(bean);
            aboutMe.setText(tutorInfosBean.getAboutMe());
            sessionInfos.setText(tutorInfosBean.getSessionInfos());
            contactNumbers.setText(tutorInfosBean.getContactNumbers());
            reviewAverage.setText(String.valueOf(tutorInfosBean.getAvgReview()));

            coursesArea.clear();
            for (String course : tutorInfosBean.getTutorCourses()) {
                coursesArea.appendText(course + "\n" );
            }

            this.tutorName.setText(tutorInfosBean.getName());
            tutorSurname.setText(tutorInfosBean.getSurname());
        }
        catch (CourseException | UserInfoException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Error in loading page, try restart the application", 300, 300, ButtonType.CLOSE);
            alert.showAndWait();
        }
    }
}
