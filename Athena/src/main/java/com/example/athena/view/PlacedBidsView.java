package com.example.athena.view;

import com.example.athena.engineering_classes.search_result_factory.FormatBundle;
import com.example.athena.engineering_classes.search_result_factory.ProductTypeEnum;
import com.example.athena.engineering_classes.search_result_factory.SearchResultFactory;
import com.example.athena.engineering_classes.search_result_factory.SearchResultProduct;
import com.example.athena.exceptions.PercentFormatException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.graphical_controller.normal_interface.PlacedBidsViewGC;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class PlacedBidsView {
    private final PlacedBidsViewGC controller;
    private final double width;
    private final double height;
    private SearchResultProduct bids ;
    private static final String FATAL_ERROR = "FATAL ERROR: The application is unable to load content. If the problem persists after restarting, try reinstalling the application.";


    public PlacedBidsView(double width, double height) {
        this.controller = new PlacedBidsViewGC(this);
        this.width = width;
        this.height = height;

    }

    public AnchorPane getRoot() {
        try{
            int size = this.controller.getResultSize();
            FormatBundle formatBundle = new FormatBundle();
            formatBundle.setContainerWidth(width);
            formatBundle.setContainerHeight(height);
            formatBundle.setEntryNumber(size);
            formatBundle.setEntryPercents(30, 20, 30, 10, 10);
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
}
