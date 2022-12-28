package com.example.athena.engineering_classes.scene_decorators;

import com.example.athena.engineering_classes.search_result_factory.FormatBundle;
import com.example.athena.graphical_controller.normal_interface.SearchResultsGraphicalController;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class VerticalScrollBarDecorator extends SearchResultFormatterDecorator {


    @Override
    public AnchorPane buildScene(FormatBundle formatBundle){
        formatBundle.setContainerWidth(formatBundle.getContainerWidth() -20);
        AnchorPane resultPane = super.buildScene(formatBundle) ;
        return applyVerticalScrollBar(resultPane, formatBundle.getContainerWidth() + 20, formatBundle.getContainerHeight(), formatBundle.getHeight()) ;
    }

    public VerticalScrollBarDecorator(SearchResultFormatterComponent component) {
        super(component);
    }

    private ScrollBar getVerticalScrollBar(double containerWidth, double containerHeight, double height)
    {
        ScrollBar scrollBar = new ScrollBar() ;
        scrollBar.setOrientation(Orientation.VERTICAL) ;
        scrollBar.setLayoutX(containerWidth -20) ;
        scrollBar.setPrefSize(20, containerHeight) ;
        scrollBar.setMin(0) ;
        scrollBar.setMax(height - containerHeight + 25.0) ;
        return scrollBar ;
    }

    private AnchorPane applyVerticalScrollBar(AnchorPane pane, double containerWidth, double containerHeight, double size)
    {
        pane.setPrefSize(containerWidth, containerHeight) ;
        ScrollBar scrollBar = this.getVerticalScrollBar(containerWidth, containerHeight, size) ;
        pane.getChildren().add(scrollBar) ;

        scrollBar.valueProperty().addListener((observableValue, number, newVal) -> {
            SearchResultsGraphicalController pageGraphController = new SearchResultsGraphicalController() ;
            pageGraphController.scrollResultsVertical((Pane) pane.lookup("#resultList"), newVal) ;
        });

        return pane ;
    }

}
