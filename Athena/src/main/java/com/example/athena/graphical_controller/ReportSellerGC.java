package com.example.athena.graphical_controller;

import com.example.athena.entities.Student;
import com.example.athena.entities.User;
import com.example.athena.use_case_controllers.ReportSellerUCC;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ReportSellerGC implements PostInitialize {

    private BookBean book;

    public void onYesBtnClick() { //if you want to test the report log with user : 'test@test.it' pass : 'test'
        ReportSellerUCC controller = new ReportSellerUCC();
        controller.reportSeller(this.book, Student.getInstance().getEmail());
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
