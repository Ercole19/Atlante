package com.example.athena.engineering_classes.scene_decorators;

import com.example.athena.graphical_controller.*;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFormatterScrollBar extends  SearchResultFormatterDecorator
{
    public SearchResultFormatterScrollBar(SearchResultFormatterComponent component) {
        super(component);
    }

    @Override
    public AnchorPane buildTutorSearchResultsScene(double containerWidth, double containerHeight, List<TutorSearchResultBean> results)
    {
        AnchorPane resultPane = super.buildTutorSearchResultsScene(containerWidth -20, containerHeight, results) ;
        return applyVerticalScrollBar(resultPane, containerWidth, containerHeight, results.size()*100.0) ;
    }

    @Override
    public AnchorPane buildEventSearchResultsScene(double containerWidth, double containerHeight, List<EventBean> results)
    {
        AnchorPane resultPane = super.buildEventSearchResultsScene(containerWidth -20, containerHeight, results) ;
        return applyVerticalScrollBar(resultPane, containerWidth, containerHeight, results.size()*100.0) ;
    }

    @Override
    public AnchorPane buildBookSearchResultsScene(double containerWidth, double containerHeight, List<BookBean> results)
    {
        AnchorPane resultPane = super.buildBookSearchResultsScene(containerWidth, containerHeight -20, results) ;
        return applyHorizontalScrollBar(resultPane, containerWidth, containerHeight, results.size()*250.0) ;
    }

    @Override
    public AnchorPane buildRecentPurchaseResultScene(double containerWidth, double containerHeight, List<BookBean> results) {
        AnchorPane resultPane = super.buildRecentPurchaseResultScene(containerWidth -20, containerHeight, results) ;
        return applyVerticalScrollBar(resultPane, containerWidth, containerHeight, results.size()*100.0) ;
    }


    private ScrollBar getVerticalScrollBar(double containerWidth, double containerHeight, double listSize)
    {
        ScrollBar scrollBar = new ScrollBar() ;
        scrollBar.setOrientation(Orientation.VERTICAL) ;
        scrollBar.setLayoutX(containerWidth -20) ;
        scrollBar.setPrefSize(20, containerHeight) ;
        scrollBar.setMin(0) ;
        scrollBar.setMax(listSize - containerHeight + 25.0) ;
        return scrollBar ;
    }

    private ScrollBar getHorizontalScrollBar(double containerWidth, double containerHeight, double listSize)
    {
        ScrollBar scrollBar = new ScrollBar() ;
        scrollBar.setOrientation(Orientation.HORIZONTAL) ;
        scrollBar.setLayoutY(containerHeight -20) ;
        scrollBar.setPrefSize(containerWidth, 20);
        scrollBar.setMin(0) ;
        scrollBar.setMax(listSize - containerHeight + 25.0) ;
        return scrollBar ;
    }

    private AnchorPane applyVerticalScrollBar(AnchorPane pane, double containerWidth, double containerHeight, double size)
    {
        pane.setPrefSize(containerWidth, containerHeight) ;
        ScrollBar scrollBar = this.getVerticalScrollBar(containerWidth, containerHeight, size) ;
        pane.getChildren().add(scrollBar) ;

        scrollBar.valueProperty().addListener((observableValue, number, newVal) -> {
            SearchResultsGraphicalController pageGraphController = new SearchResultsGraphicalController() ;
            pageGraphController.scrollResultsVertical((VBox) pane.lookup("#resultList"), newVal) ;
        });

        return pane ;
    }

    private AnchorPane applyHorizontalScrollBar(AnchorPane pane, double containerWidth, double containerHeight, double size)
    {
        pane.setPrefSize(containerWidth, containerHeight) ;
        ScrollBar scrollBar = this.getHorizontalScrollBar(containerWidth, containerHeight, size) ;
        pane.getChildren().add(scrollBar) ;

        scrollBar.valueProperty().addListener((observableValue, number, newVal) -> {
            SearchResultsGraphicalController pageGraphController = new SearchResultsGraphicalController() ;
            pageGraphController.scrollResultsHorizontal((HBox) pane.lookup("#resultList"), newVal);
        });

        return pane ;
    }
}
