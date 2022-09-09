package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.normal.CourseBean;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.use_case_controllers.CourseUCC;
import com.example.athena.view.oracle_view.LabelView;

public class OracleAddCourseGC {
    
    public void addCourse(String courseName) {
        LabelView labelView = new LabelView();
        CourseBean bean = new CourseBean();
        bean.setCourse(courseName);
        CourseUCC controller = new CourseUCC();
        try {
            controller.addNewCourse(bean);
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Course added"));
        } catch (CourseException | UserInfoException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Error in adding course, check if it already exists"));
        }
    }
}
