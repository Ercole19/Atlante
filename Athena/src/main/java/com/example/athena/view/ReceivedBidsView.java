package com.example.athena.view;

import com.example.athena.engineering_classes.search_result_factory.FormatBundle;
import com.example.athena.engineering_classes.search_result_factory.ProductTypeEnum;
import com.example.athena.engineering_classes.search_result_factory.SearchResultFactory;
import com.example.athena.engineering_classes.search_result_factory.SearchResultProduct;
import com.example.athena.exceptions.PercentFormatException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.graphical_controller.normal_interface.ReceivedBidsViewGC;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class ReceivedBidsView {
    private final ReceivedBidsViewGC controller;
    private final double width;
    private final double height;
    private final double acceptedWidth ;
    private final double acceptedHeight ;
    private SearchResultProduct bids;
    private SearchResultProduct acceptedBid ;
    private static final String FATAL_ERROR = "FATAL ERROR: The application is unable to load content. If the problem persists after restarting, try reinstalling the application.";


    public ReceivedBidsView(double width, double height, double acceptedWidth, double acceptedHeight) {
        this.controller = new ReceivedBidsViewGC(this);
        this.width = width;
        this.height = height;
        this.acceptedHeight = acceptedHeight;
        this.acceptedWidth = acceptedWidth ;
    }

    public AnchorPane getRoot(String seller, String isbn, String timestamp) {
        try{
            int size = this.controller.getResultSize(seller, isbn, timestamp);
            FormatBundle formatBundle = new FormatBundle();
            formatBundle.setContainerWidth(width);
            formatBundle.setContainerHeight(height);
            formatBundle.setEntryNumber(size);
            formatBundle.setEntryPercents(50, 30, 10, 10);
            formatBundle.setEntrySize(100);
            this.bids = SearchResultFactory.createProduct(ProductTypeEnum.VERTICAL_ENTRY, formatBundle);
            this.controller.setValues(bids);
        }
        catch (PercentFormatException e){
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, FATAL_ERROR, 800, 600) ;
            alert.showAndWait() ;
            System.exit(1) ;
        }
        return bids.getRoot();
    }

    public AnchorPane getAcceptedBidRoot(String seller, String isbn, String timestamp) {
        try{
            boolean isPresent = this.controller.isThereAnAcceptedBid(seller, isbn, timestamp) ;

            if (isPresent) {
                FormatBundle formatBundle = new FormatBundle();
                formatBundle.setContainerWidth(acceptedWidth);
                formatBundle.setContainerHeight(acceptedHeight);
                formatBundle.setEntryNumber(1);
                formatBundle.setEntryPercents(50, 30, 20);
                formatBundle.setEntrySize(acceptedHeight);
                this.acceptedBid = SearchResultFactory.createProduct(ProductTypeEnum.VERTICAL_ENTRY, formatBundle);
                this.controller.setAcceptedBidValues(acceptedBid) ;
            }

        }
        catch (PercentFormatException e){
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, FATAL_ERROR, 800, 600) ;
            alert.showAndWait() ;
            System.exit(1) ;
        }
        return bids.getRoot();
    }
}
