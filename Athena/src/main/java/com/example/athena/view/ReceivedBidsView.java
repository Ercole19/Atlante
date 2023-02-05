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
    private SearchResultProduct receivedBids;
    private static final String FATAL_ERROR = "FATAL ERROR: The application is unable to load content. If the problem persists after restarting, try reinstalling the application.";


    public ReceivedBidsView(double width, double height) {
        this.controller = new ReceivedBidsViewGC(this);
        this.width = width;
        this.height = height;
    }

    public AnchorPane getRoot(String seller, String isbn, String timestamp) {
        try{
            int size = this.controller.getResultSize(seller, isbn, timestamp);
            FormatBundle formatBundle = new FormatBundle();
            formatBundle.setContainerWidth(width);
            formatBundle.setContainerHeight(height);
            formatBundle.setEntryNumber(size);
            formatBundle.setEntryPercents(30, 15, 25, 15, 15);
            formatBundle.setEntrySize(100);
            formatBundle.setWidth(width);
            formatBundle.setHeight(formatBundle.getEntrySize()* formatBundle.getEntryNumber());
            this.receivedBids = SearchResultFactory.createProduct(ProductTypeEnum.VERTICAL_ENTRY, formatBundle);
            this.controller.setValues(this.receivedBids);
        }
        catch (PercentFormatException e){
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, FATAL_ERROR, 800, 600) ;
            alert.showAndWait() ;
            System.exit(1) ;
        }
        return this.receivedBids.getRoot();
    }

}