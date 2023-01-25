package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.TutorInfosBean;
import com.example.athena.beans.UserBean;
import com.example.athena.entities.LoggedTutor;
import com.example.athena.entities.TutorPersonalPageSubject;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.view.oracle_view.LabelView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowMyCoursesGC implements Initializable {

    @FXML
    TextArea coursesArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserBean userBean = new UserBean();
        userBean.setEmail(LoggedTutor.getInstance().getEmail());
        try {
            TutorInfosBean bean = TutorPersonalPageSubject.getInstance().getTutorInfos(userBean);
            for (String course : bean.getTutorCourses()) {
                coursesArea.appendText(course + "\n" );
            }
        } catch (CourseException | UserInfoException e) {
            ParentSubject.getInstance().setCurrentParent(new LabelView().prepareParent("Error in retrieving infos, details follow: " + e.getMessage()));
        }
    }
}
