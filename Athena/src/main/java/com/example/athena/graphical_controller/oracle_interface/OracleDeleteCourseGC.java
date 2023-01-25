package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.CourseBean;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.use_case_controllers.CourseUCC;
import com.example.athena.view.oracle_view.LabelView;

public class OracleDeleteCourseGC {
    public void deleteCourse(String name) {
        LabelView labelView = new LabelView();
        CourseBean bean = new CourseBean();
        bean.setCourse(name);
        CourseUCC controller = new CourseUCC();
        try {
            controller.deleteCourse(bean);
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Course deleted"));
        } catch (CourseException | UserInfoException e) {
            ParentSubject.getInstance().setCurrentParent(labelView.prepareParent("Error in deleting course, check if it already exists"));
        }
    }
}
