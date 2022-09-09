package com.example.athena.use_case_controllers;

import com.example.athena.beans.normal.CourseBean;
import com.example.athena.entities.TutorPersonalPageSubject;
import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.UserInfoException;

public class CourseUCC {

    public void addNewCourse(CourseBean bean) throws CourseException, UserInfoException {
        TutorPersonalPageSubject.getInstance().addCourse(bean.getCourse());
    }

    public void deleteCourse(CourseBean bean) throws CourseException, UserInfoException {
        TutorPersonalPageSubject.getInstance().deleteCourse(bean.getCourse());
    }
}
