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

}
