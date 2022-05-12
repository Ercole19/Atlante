package com.example.athena.view;

import com.example.athena.engineering_classes.abstract_factory.Factory;
import com.example.athena.engineering_classes.abstract_factory.FormatBundle;
import com.example.athena.engineering_classes.abstract_factory.ProductTypeEnum;
import com.example.athena.engineering_classes.abstract_factory.SearchResultProduct;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.FindBookException;
import com.example.athena.exceptions.PercentFormatException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.graphical_controller.FindBooksViewGC;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class FindBooksView {

    private final FindBooksViewGC controller ;
    private final double containerWidth ;
    private final double containerHeight ;
    private SearchResultProduct result ;
    private static final String FATAL_ERROR = "FATAL ERROR: The application is unable to load content. If the problem persists after restarting, try reinstalling the application.";

    public FindBooksView(double containerWidth, double containerHeight)
    {
        this.containerHeight = containerHeight ;
        this.containerWidth = containerWidth ;
        this.controller = new FindBooksViewGC(this) ;
    }

    public AnchorPane getRoot(String query) {
        try
        {
            int size = this.controller.getResultSize(query);
            FormatBundle formatBundle = new FormatBundle();
            formatBundle.setContainerWidth(containerWidth);
            formatBundle.setContainerHeight(containerHeight);
            formatBundle.setEntryNumber(size);
            formatBundle.setEntrySize(150);
            formatBundle.setEntryPercents(40, 20, 20, 20);
            this.result = Factory.createProduct(ProductTypeEnum.HORIZONTAL_ENTRY, formatBundle);
            this.controller.setValues(this.result, formatBundle.getEntrySize());
        }
        catch (PercentFormatException | BookException exc)
        {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, FATAL_ERROR, 800, 600) ;
            alert.showAndWait() ;
            System.exit(1) ;
        }
        catch (FindBookException ex) {
            return new ErrorSceneView().createErrorScreen("No book has been found.", containerWidth, containerHeight);
        }
        return result.getRoot();
    }
}
