package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.BookBean;
import com.example.athena.beans.RecentBooksSearchResultBean;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ReportException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.ReportSellerUCC;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ReportSellerGC implements PostInitialize {

    private RecentBooksSearchResultBean book;

    public void onYesBtnClick(ActionEvent event) {
        try {
            ReportSellerUCC controller = new ReportSellerUCC();
            controller.reportSeller(this.book);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
        catch (ReportException e){
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
        this.book = (RecentBooksSearchResultBean) params.get(0);
    }





}
