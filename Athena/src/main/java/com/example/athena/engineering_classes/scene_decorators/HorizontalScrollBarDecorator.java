package com.example.athena.engineering_classes.scene_decorators;

import com.example.athena.engineering_classes.search_result_factory.FormatBundle;
import com.example.athena.graphical_controller.normal_interface.SearchResultsGraphicalController;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class HorizontalScrollBarDecorator extends SearchResultFormatterDecorator{

    @Override
    public AnchorPane buildScene(FormatBundle formatBundle){
        formatBundle.setContainerHeight(formatBundle.getContainerHeight() -20);
        AnchorPane resultPane = super.buildScene(formatBundle) ;
        return applyHorizontalScrollBar(resultPane, formatBundle.getContainerWidth(), formatBundle.getContainerHeight() + 20, formatBundle.getWidth()) ;
    }

    public HorizontalScrollBarDecorator(SearchResultFormatterComponent component) {
        super(component);
    }

    private ScrollBar getHorizontalScrollBar(double containerWidth, double containerHeight, double width)
    {
        ScrollBar scrollBar = new ScrollBar() ;
        scrollBar.setOrientation(Orientation.HORIZONTAL) ;
        scrollBar.setLayoutY(containerHeight -20) ;
        scrollBar.setPrefSize(containerWidth, 20);
        scrollBar.setMin(0) ;
        scrollBar.setMax(width - containerWidth + 25.0) ;
        return scrollBar ;
    }

    private AnchorPane applyHorizontalScrollBar(AnchorPane pane, double containerWidth, double containerHeight, double size)
    {
        pane.setPrefSize(containerWidth, containerHeight) ;
        ScrollBar scrollBar = this.getHorizontalScrollBar(containerWidth, containerHeight, size) ;
        pane.getChildren().add(scrollBar) ;

        scrollBar.valueProperty().addListener((observableValue, number, newVal) -> {
            SearchResultsGraphicalController pageGraphController = new SearchResultsGraphicalController() ;
            pageGraphController.scrollResultsHorizontal((Pane) pane.lookup("#resultList"), newVal);
        });

        return pane ;
    }
}
