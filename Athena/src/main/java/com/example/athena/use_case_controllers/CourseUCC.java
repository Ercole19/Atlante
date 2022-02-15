package com.example.athena.use_case_controllers;

import com.example.athena.entities.CourseDao;

public class CourseUCC {

    public void addNewCourse(String name) {
        CourseDao dao = new CourseDao() ;
        dao.addCourse(name);
    }

    public boolean deleteCourse(String name) {
        CourseDao dao = new CourseDao();
        if (dao.checkCourseExist(name)){
            dao.deleteCourse(name);
            return true;
        }
        else {
            return false;
        }

    }
}
