package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.normal.BidBean;
import com.example.athena.beans.normal.BookBean;
import com.example.athena.engineering_classes.search_result_factory.SearchResultProduct;
import com.example.athena.entities.BidStatusEnum;
import com.example.athena.exceptions.BidException;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.SizedAlert;
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
    private final GetReceivedBidsUCC controller = new GetReceivedBidsUCC();
    private final ManageBidsUCC manageBidsUCC = new ManageBidsUCC();
    private BidBean acceptedBid ;

    public ReceivedBidsViewGC(ReceivedBidsView view) {
        this.view = view;
    }

    public int getResultSize(String seller, String isbn, String timestamp) {
        try {
            BookBean bean = new BookBean();
            bean.setOwner(seller);
            bean.setIsbn(isbn);
            bean.setTimeStamp(timestamp);
            this.results =  controller.getBids(bean);
        } catch (BidException | BookException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(),800, 600);
            alert.showAndWait();
        }
        return this.results.size() ;
    }

    public boolean isThereAnAcceptedBid(String seller, String isbn, String timestamp) {
        try {
            this.acceptedBid = controller.getAcceptedBid(seller, isbn, timestamp) ;
            return true ;
        } catch (BidException e) {
            return false ;
        }


    }

    public void setAcceptedBidValues(SearchResultProduct product){
        product.setLegend(0, LabelBuilder.buildLabel("Bidder"));
        product.setLegend(1, LabelBuilder.buildLabel("Offer"));

        product.setEntry(0, 0, LabelBuilder.buildLabel(acceptedBid.getBidder()));
        product.setEntry(0, 1, LabelBuilder.buildLabel(acceptedBid.getNewPrice()));
        Button cancel = new Button("cancel");
        cancel.setOnAction(event -> {
            try {
                acceptedBid.setStatus(BidStatusEnum.PENDING.toString());
                this.manageBidsUCC.updateBid(acceptedBid);
                refreshScreen(acceptedBid);
            }
            catch (BidException e){
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800, 600);
                alert.showAndWait();
            }
        });
        product.setEntry(0, 2, cancel);
    }

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
                        this.manageBidsUCC.updateBid(bidBean);
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
                        this.manageBidsUCC.updateBid(bidBean);
                        refreshScreen(bidBean);
                    }
                    catch(BidException e){
                        SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage(), 800, 600);
                        alert.showAndWait();
                    }
                });

                if (isThereAnAcceptedBid(bidBean.getOwner(), bidBean.getBookIsbn(), bidBean.getBookTimestamp())) {
                    accept.setDisable(true);
                    refuse.setDisable(true);
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
        SceneSwitcher.getInstance().switcher("ManageBidsPage.fxml", params) ;
    }
}
