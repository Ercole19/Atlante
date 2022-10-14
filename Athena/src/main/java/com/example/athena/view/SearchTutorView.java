package com.example.athena.view;

import com.example.athena.engineering_classes.search_result_factory.SearchResultFactory;
import com.example.athena.engineering_classes.search_result_factory.FormatBundle;
import com.example.athena.engineering_classes.search_result_factory.ProductTypeEnum;
import com.example.athena.engineering_classes.search_result_factory.SearchResultProduct;
import com.example.athena.entities.ByCourseOrNameEnum;
import com.example.athena.exceptions.FindTutorException;
import com.example.athena.exceptions.PercentFormatException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.graphical_controller.normal_interface.SearchTutorViewGC;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class SearchTutorView {
    private final SearchTutorViewGC controller ;
    private final double containerWidth ;
    private final double containerHeight ;
    private static final String FATAL_ERROR = "FATAL ERROR: The application is unable to load content. If the problem persists after restarting, try reinstalling the application.";


    public SearchTutorView(double containerWidth, double containerHeight)
    {
        this.containerWidth = containerWidth ;
        this.containerHeight = containerHeight ;
        this.controller = new SearchTutorViewGC(this) ;
    }

    public AnchorPane getRoot(String query, ByCourseOrNameEnum type, boolean ordered) {
        SearchResultProduct result = null;
        try
        {
            int size = this.controller.getResultSize(query, type, ordered);

            FormatBundle formatBundle = new FormatBundle();
            formatBundle.setContainerWidth(containerWidth);
            formatBundle.setContainerHeight(containerHeight);
            formatBundle.setEntryNumber(size);
            formatBundle.setEntryPercents(30, 30, 30, 10);
            formatBundle.setEntrySize(100) ;
            result = SearchResultFactory.createProduct(ProductTypeEnum.VERTICAL_ENTRY, formatBundle);
            this.controller.setValues(result);
        }
        catch (PercentFormatException exc)
        {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, FATAL_ERROR, 800, 600) ;
            alert.showAndWait() ;
            System.exit(1) ;
        }
        catch (FindTutorException exception){
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, exception.getMessage(), 800, 600) ;
            alert.showAndWait() ;
            return new ErrorSceneView().createErrorScreen("No tutor has been found.", containerWidth, containerHeight) ;
        }
        return result.getRoot();
    }
}

