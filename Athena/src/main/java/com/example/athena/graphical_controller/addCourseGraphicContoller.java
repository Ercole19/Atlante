package com.example.athena.graphical_controller;
import com.example.athena.entities.CourseDao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class addCourseGraphicContoller {
    private CourseDao corso ;
    @FXML
    private TextField coursefield ;


    public void onAddClick ( ) {
        corso = new CourseDao() ;
        corso.addCourse(coursefield.getText()) ;

    }

    public void onDeleteClick () {
        corso = new CourseDao() ;
        corso.deleteCourse (coursefield.getText()) ;
    }




}
