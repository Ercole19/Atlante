package com.example.athena.view;

import com.example.athena.engineering_classes.abstract_factory.Factory;
import com.example.athena.engineering_classes.abstract_factory.FormatBundle;
import com.example.athena.engineering_classes.abstract_factory.ProductTypeEnum;
import com.example.athena.engineering_classes.abstract_factory.SearchResultProduct;
import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.PercentFormatException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.graphical_controller.EventPageGC;
import com.example.athena.graphical_controller.EventsViewGC;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

public class EventsView {
    private final EventsViewGC controller ;
    private final double containerWidth ;
    private final double containerHeight ;
    private SearchResultProduct result ;
    private static final String FATAL_ERROR = "FATAL ERROR: The application is unable to load content. If the problem persists after restarting, try reinstalling the application.";

    public EventsView (double containerWidth, double containerHeight) {
        this.containerHeight = containerHeight;
        this.containerWidth = containerWidth;
        this.controller = new EventsViewGC(this);
    }

    public AnchorPane getRoot (LocalDate date){
        try {
            int size = this.controller.getResultSize(date);
            FormatBundle formatBundle = new FormatBundle();
            formatBundle.setContainerWidth(containerWidth);
            formatBundle.setContainerHeight(containerHeight);
            formatBundle.setEntryNumber(size);
            formatBundle.setEntryPercents(20, 15, 15, 20, 10, 10, 10);
            formatBundle.setEntrySize(100);
            this.result = Factory.createProduct(ProductTypeEnum.VERTICAL_ENTRY, formatBundle);
            this.controller.setValues(this.result);
        }
        catch (PercentFormatException | EventException exc) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, FATAL_ERROR, 800, 600) ;
            alert.showAndWait() ;
            System.exit(1) ;
        }

        return result.getRoot();

    }
}
