package com.example.athena.engineering_classes.scene_decorators;

import com.example.athena.graphical_controller.BookBean;
import com.example.athena.graphical_controller.BookSearchResultBean;
import com.example.athena.graphical_controller.EventBean;
import com.example.athena.graphical_controller.TutorSearchResultBean;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchResultFormatterDecorator extends SearchResultFormatterComponent {
    private final SearchResultFormatterComponent component;

    protected SearchResultFormatterDecorator(SearchResultFormatterComponent component) {
        this.component = component;
    }

    @Override
    public AnchorPane buildTutorSearchResultsScene(double containerWidth, double containerHeight, List<TutorSearchResultBean> results) {
        return this.component.buildTutorSearchResultsScene(containerWidth, containerHeight, results);
    }

    @Override
    public AnchorPane buildEventSearchResultsScene(double containerWidth, double containerHeight, List<EventBean> results) {
        return this.component.buildEventSearchResultsScene(containerWidth, containerHeight, results);
    }

    @Override
    public AnchorPane buildBookSearchResultsScene(double containerWidth, double containerHeight, List<BookBean> results) {
        return this.component.buildBookSearchResultsScene(containerWidth, containerHeight, results);
    }

    @Override
    public AnchorPane buildRecentPurchaseResultScene(double coontainerWidth, double containerHeight, List<BookBean> results) {
         return this.component.buildRecentPurchaseResultScene(coontainerWidth, containerHeight, results);
    }


}
