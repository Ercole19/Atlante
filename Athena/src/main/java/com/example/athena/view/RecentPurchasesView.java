package com.example.athena.view;

import com.example.athena.engineering_classes.search_result_factory.SearchResultFactory;
import com.example.athena.engineering_classes.search_result_factory.FormatBundle;
import com.example.athena.engineering_classes.search_result_factory.ProductTypeEnum;
import com.example.athena.engineering_classes.search_result_factory.SearchResultProduct;
import com.example.athena.entities.Student;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.PercentFormatException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.graphical_controller.normal_interface.RecentPurchasesViewGC;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class RecentPurchasesView {

    private final RecentPurchasesViewGC controller ;
    private final double containerWidth ;
    private final double containerHeight ;
    private SearchResultProduct result ;
    private static final String FATAL_ERROR = "FATAL ERROR: The application is unable to load content. If the problem persists after restarting, try reinstalling the application.";

    public RecentPurchasesView(double containerWidth, double containerHeight) {
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
        this.controller = new RecentPurchasesViewGC();
    }

    public AnchorPane getRoot() {
        try {
            int size = this.controller.getResultSize(Student.getInstance().getEmail());
            if(size == 0)
            {
                return new ErrorSceneView().createErrorScreen("No purchases made yet", this.containerWidth, this.containerHeight) ;
            }

            FormatBundle formatBundle = new FormatBundle();
            formatBundle.setContainerWidth(containerWidth);
            formatBundle.setContainerHeight(containerHeight);
            formatBundle.setEntryNumber(size);
            formatBundle.setEntryPercents(30, 15, 30, 15, 10);
            formatBundle.setEntrySize(100);
            this.result = SearchResultFactory.createProduct(ProductTypeEnum.VERTICAL_ENTRY, formatBundle);
            this.controller.setValues(this.result);

        }
        catch (PercentFormatException | BookException exception) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, FATAL_ERROR, 800, 600) ;
            alert.showAndWait() ;
            System.exit(1) ;
        }
        return result.getRoot();
    }

}
