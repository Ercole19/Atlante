package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.BidBean;
import com.example.athena.beans.BookBean;
import com.example.athena.engineering_classes.search_result_factory.SearchResultProduct;
import com.example.athena.entities.BidStatusEnum;
import com.example.athena.exceptions.BidException;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.use_case_controllers.GetReceivedBidsUCC;
import com.example.athena.use_case_controllers.ManageBidsUCC;
import com.example.athena.view.LabelBuilder;
import com.example.athena.view.ReceivedBidsView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class ReceivedBidsViewGC {
    private static final String FONT = "System";
    private List<BidBean> results;
    private final ReceivedBidsView view;
    private final GetReceivedBidsUCC getReceivedBidsUCC = new GetReceivedBidsUCC();
    private final ManageBidsUCC manageBidsUCC = new ManageBidsUCC();
    private Boolean isThereAnAcceptedBid = null;

    public ReceivedBidsViewGC(ReceivedBidsView view) {
        this.view = view;
    }

    public int getResultSize(String seller, String isbn, String timestamp) {
        try {
            BidBean bean = new BidBean();
            bean.setOwner(seller);
            bean.setBookIsbn(isbn);
            bean.setBookTimestamp(timestamp);
            this.results =  getReceivedBidsUCC.getBids(bean);
        } catch (BidException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(),800, 600);
            alert.showAndWait();
        }
        return this.results.size() ;
    }


    /*private boolean isThereAnAcceptedBid() {
        if (this.isThereAnAcceptedBid == null) {
            this.isThereAnAcceptedBid = false;
            for (BidBean bid : this.results) {
                if (bid.getStatus().equals("Accepted")) {
                    this.isThereAnAcceptedBid = true;
                    break;
                }
            }
        }
        return isThereAnAcceptedBid;
    }*/


    public void setValues(SearchResultProduct product){
        product.setLegend(0, LabelBuilder.buildLabel("Bidder"));
        product.setLegend(1, LabelBuilder.buildLabel("Offer"));
        product.setLegend(2, LabelBuilder.buildLabel("Status"));

        try
        {
            int i = 0 ;
            for(BidBean bidBean: this.results)
            {

                product.setEntry(i, 0, LabelBuilder.buildLabel(bidBean.getBidder())) ;

                product.setEntry(i, 1, LabelBuilder.buildLabel(bidBean.getNewPrice())) ;

                product.setEntry(i, 2, LabelBuilder.buildLabel(bidBean.getStatus())) ;

                Button accept = new Button("Accept");
                accept.setFont(new Font(FONT,26));
                product.setEntry(i,3, accept);
                accept.setOnAction(event -> {
                    try {
                        bidBean.setStatus(BidStatusEnum.ACCEPTED.toString());
                        this.manageBidsUCC.updateBidStatus(bidBean);
                        refreshScreen(bidBean);
                    }
                    catch(BidException e){
                        SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800, 600);
                        alert.showAndWait();
                    }
                });

                Button refuse = new Button("Refuse");
                refuse.setFont(new Font(FONT, 26));
                product.setEntry(i, 4, refuse);
                refuse.setOnAction(event -> {
                    try {
                        bidBean.setStatus(BidStatusEnum.REJECTED.toString());
                        this.manageBidsUCC.updateBidStatus(bidBean);
                        refreshScreen(bidBean);
                    }
                    catch(BidException e){
                        SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800, 600);
                        alert.showAndWait();
                    }
                });

                if (bidBean.getStatus().equals("Accepted")) {
                    accept.setDisable(true);
                    refuse.setDisable(false);
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

    private void refreshScreen(BidBean book) {
        ArrayList<Object> params = new ArrayList<>() ;
        params.add(book);
        this.isThereAnAcceptedBid = null;
        if (System.getProperty("oracle").equals("false"))SceneSwitcher.getInstance().switcher("ManageBidsPage.fxml", params) ;
        else ParentSubject.getInstance().setCurrentParent(SceneSwitcher.getInstance().preload("OracleManageBids.fxml", params));
    }
}