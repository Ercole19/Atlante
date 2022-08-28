package com.example.athena.use_case_controllers;

import com.example.athena.entities.TutorPersonalPageSubject;
import com.example.athena.exceptions.CourseException;

public class CourseUCC {

    public void addNewCourse(String name) throws CourseException {
        TutorPersonalPageSubject.getInstance().addCourse(name);
    }

    public void deleteCourse(String name) throws CourseException {
        TutorPersonalPageSubject.getInstance().deleteCourse(name);
    }
}
