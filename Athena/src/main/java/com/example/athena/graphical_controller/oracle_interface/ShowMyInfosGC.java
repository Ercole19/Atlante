package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.TutorInfosBean;
import com.example.athena.beans.UserBean;
import com.example.athena.entities.Tutor;
import com.example.athena.entities.TutorPersonalPageSubject;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.use_case_controllers.TutorPersonalPageUCC;
import com.example.athena.view.oracle_view.LabelView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowMyInfosGC implements Initializable {
    @FXML
    TextArea description;
    @FXML
    TextArea tutInfos;
    @FXML
    TextArea contactNumbers;
    private final LabelView view = new LabelView();

    public void onConfirmButtonClick() {
        TutorInfosBean bean = new TutorInfosBean();
        bean.setAboutMe(description.getText());
        bean.setSessionInfos(tutInfos.getText());
        bean.setContactNumbers(contactNumbers.getText());
        TutorPersonalPageUCC controller = new TutorPersonalPageUCC();
        try {
            controller.updateTutorInformation(bean);
        } catch (UserInfoException | CourseException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in saving infos, details follow: " + e.getMessage()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserBean userBean = new UserBean();
        userBean.setEmail(Tutor.getInstance().getEmail());
        try {
            TutorInfosBean bean = TutorPersonalPageSubject.getInstance().getTutorInfos(userBean);
            description.setText(bean.getAboutMe());
            tutInfos.setText(bean.getSessionInfos());
            contactNumbers.setText(bean.getContactNumbers());
        } catch (CourseException | UserInfoException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in retrieving infos, details follow: " + e.getMessage()));
        }
    }
}
