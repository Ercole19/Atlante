package com.example.athena.GraphicalController;
import com.example.athena.Entities.coursedao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class addCourseGraphicContoller {
    private coursedao corso ;
    @FXML
    private TextField coursefield ;


    public void onAddClick ( ) {
        corso = new coursedao() ;
        corso.addCourse(coursefield.getText()) ;

    }

    public void onDeleteClick () {
        corso = new coursedao() ;
        corso.deleteCourse (coursefield.getText()) ;
    }




}
