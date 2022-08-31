package com.example.athena.graphical_controller;

import com.example.athena.exceptions.CourseException;
import com.example.athena.exceptions.SizedAlert;
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
        if (courseField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Must insert a course name", ButtonType.CLOSE);
            alert.showAndWait();
        }
        else {
            try {
                controller.addNewCourse(courseField.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Course added!", ButtonType.CLOSE);
                alert.showAndWait();
                Stage stage = (Stage) courseField.getScene().getWindow();
                stage.close();
            }
            catch (CourseException e) {
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Error in adding course, details follow: " + e.getMessage(), 200, 200, ButtonType.CLOSE);
                alert.showAndWait();
            }
        }
    }

    public void onDeleteClick () {
        CourseUCC controller = new CourseUCC() ;
        if (courseField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Must insert a course name", ButtonType.CLOSE);
            alert.showAndWait();
        }
        else {
           try {
               controller.deleteCourse(courseField.getText());
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Course deleted!", ButtonType.CLOSE);
               alert.showAndWait();
               Stage stage = (Stage) courseField.getScene().getWindow();
               stage.close();
           }
           catch (CourseException e) {
               SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Error in deleting course, details follow: " + e.getMessage(), 200, 200, ButtonType.CLOSE);
               alert.showAndWait();
           }
        }
    }
    public void onBackButtonClick(){
        Stage stage = (Stage) courseField.getScene().getWindow();
        stage.close();
    }
}
