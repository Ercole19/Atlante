package com.example.athena.view;

import com.example.athena.engineering_classes.abstract_factory.Factory;
import com.example.athena.engineering_classes.abstract_factory.FormatBundle;
import com.example.athena.engineering_classes.abstract_factory.ProductTypeEnum;
import com.example.athena.engineering_classes.abstract_factory.SearchResultProduct;
import com.example.athena.entities.ByCourseOrNameEnum;
import com.example.athena.exceptions.PercentFormatException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.graphical_controller.SearchTutorViewGC;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class SearchTutorView {
    private SearchTutorViewGC controller ;
    private double containerWidth ;
    private double containerHeight ;
    private SearchResultProduct result ;
    private static final String FATAL_ERROR = "FATAL ERROR: The application is unable to load content. If the problem persists after restarting, try reinstalling the application.";


    public SearchTutorView()
    {
        this.controller = new SearchTutorViewGC(this) ;
    }

    public AnchorPane getRoot(String query, ByCourseOrNameEnum type, boolean ordered) {
        try
        {
            int size = this.controller.getResultSize(query, type, ordered);
            FormatBundle formatBundle = new FormatBundle();
            formatBundle.setContainerWidth(containerWidth);
            formatBundle.setContainerHeight(containerHeight);
            formatBundle.setEntryNumber(size);
            formatBundle.setEntryPercents(30, 30, 30, 10);
            this.result = Factory.createProduct(ProductTypeEnum.VERTICAL_ENTRY, formatBundle);
            this.controller.setValues(this.result);
        }
        catch (PercentFormatException exc)
        {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, FATAL_ERROR, 800, 600) ;
            alert.showAndWait() ;
            System.exit(1) ;
        }
        return result.getRoot();
    }
}

