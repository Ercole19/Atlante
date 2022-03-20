package com.example.athena.graphical_controller;

import com.example.athena.use_case_controllers.CourseUCC;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CourseGraphicController {
    @FXML
    private TextField courseField;

    public void onAddClick ( ) {
        CourseUCC controller = new CourseUCC() ;
        controller.addNewCourse(courseField.getText());
        Stage stage = (Stage) courseField.getScene().getWindow();
        stage.close();
    }

    public void onDeleteClick () {
        CourseUCC controller = new CourseUCC() ;
        if (controller.deleteCourse(courseField.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Course deleted!", ButtonType.CLOSE);
            alert.showAndWait();
            Stage stage = (Stage) courseField.getScene().getWindow();
            stage.close();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Course not found", ButtonType.CLOSE);
            alert.showAndWait();
        }
    }
    public void onBackButtonClick(){
        Stage stage = (Stage) courseField.getScene().getWindow();
        stage.close();
    }
}
