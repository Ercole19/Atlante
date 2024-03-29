package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.entities.Student;
import com.example.athena.beans.normal.BookBean;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.ReportSellerUCC;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ReportSellerGC implements PostInitialize {

    private BookBean book;

    public void onYesBtnClick() { //if you want to test the report log with user : 'test@test.it' pass : 'test'
        try {
            ReportSellerUCC controller = new ReportSellerUCC();
            controller.reportSeller(this.book, Student.getInstance().getEmail());
        }
        catch (BookException e){
            if (e.getMessage().contains("Duplicate entry")) {
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Seller already reported ", ButtonType.CLOSE);
                alert.showAndWait();
            }
            else {
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, "Error in reporting seller, details follow: " + e.getMessage(), ButtonType.CLOSE);
                alert.showAndWait();
            }
            }
        }


    public void onNoBtnClick(ActionEvent event)  {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void postInitialize(ArrayList<Object> params) {
        this.book = (BookBean) params.get(0);
    }





}
