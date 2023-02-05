package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.BidBean;
import com.example.athena.engineering_classes.search_result_factory.SearchResultProduct;
import com.example.athena.exceptions.BidException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.use_case_controllers.ManageBidsUCC;
import com.example.athena.use_case_controllers.SeePlacedBidsUCC;
import com.example.athena.view.LabelBuilder;
import com.example.athena.view.PlacedBidsView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.util.List;

public class PlacedBidsViewGC {
    private PlacedBidsView view;
    private final SeePlacedBidsUCC seePlacedBidsUCC = new SeePlacedBidsUCC();
    private final ManageBidsUCC manageBidsUCC = new ManageBidsUCC();
    private List<BidBean> results;

    public PlacedBidsViewGC (PlacedBidsView view) {
        this.view = view;
    }

    public int getResultSize() {
        try {
            this.results =  seePlacedBidsUCC.getPlacedBids();
        } catch (BidException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(),800, 600);
            alert.showAndWait();
        }
        return this.results.size() ;
    }

    public void setValues(SearchResultProduct product){
        product.setLegend(0, LabelBuilder.buildLabel("ISBN"));
        product.setLegend(1, LabelBuilder.buildLabel("Offer"));
        product.setLegend(2, LabelBuilder.buildLabel("Status"));

        try
        {
            int i = 0 ;
            for(BidBean bidBean: this.results)
            {

                product.setEntry(i, 0, LabelBuilder.buildLabel(bidBean.getBookIsbn())) ;

                product.setEntry(i, 1, LabelBuilder.buildLabel(bidBean.getNewPrice()));

                product.setEntry(i, 2, LabelBuilder.buildLabel(bidBean.getStatus())) ;

                Button pay = new Button("Pay");
                product.setEntry(i,3, pay);
                pay.setOnAction(event -> {
                    try {
                        manageBidsUCC.payBid(bidBean);
                        refreshScreen();
                    }
                    catch(BidException e){
                        SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800, 600);
                        alert.showAndWait();
                    }
                });

                Button cancel = new Button("Cancel bid");
                product.setEntry(i, 4, cancel);
                cancel.setOnAction(event -> {
                    try {
                        manageBidsUCC.cancelBid(bidBean);
                        refreshScreen();
                    }
                    catch(BidException e){
                        SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800, 600);
                        alert.showAndWait();
                    }
                });

                if(bidBean.getStatus().equals("PENDING") || bidBean.getStatus().equals("REJECTED")){
                    pay.setDisable(true);
                    pay.setVisible(false);
                }

                i++ ;
            }
        }
        catch(IndexOutOfBoundsException e)
        {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800, 600);
            alert.showAndWait();
        }
    }

    private void refreshScreen() {
        if (System.getProperty("oracle").equals("false"))SceneSwitcher.getInstance().switcher("ManageBidsPage.fxml") ;
        else ParentSubject.getInstance().setCurrentParent(SceneSwitcher.getInstance().preload("OracleManageBids.fxml")); 
    }
}