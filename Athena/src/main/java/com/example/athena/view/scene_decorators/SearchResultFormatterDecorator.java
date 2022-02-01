package com.example.athena.view.scene_decorators;

import com.example.athena.graphical_controller.TutorSearchResultBean;
import com.example.athena.graphical_controller.EventBean;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public abstract class SearchResultFormatterDecorator extends SearchResultFormatterComponent
{
    private final SearchResultFormatterComponent component ;

    protected SearchResultFormatterDecorator(SearchResultFormatterComponent component)
    {
        this.component = component ;
    }

    @Override
    public AnchorPane buildTutorSearchResultsScene(double containerWidth, double containerHeight, ArrayList<TutorSearchResultBean> results)
    {
        return this.component.buildTutorSearchResultsScene(containerWidth, containerHeight, results);
    }

    @Override
    public AnchorPane buildEventSearchResultsScene(double containerWidth, double containerHeight, ArrayList<EventBean> results)
    {
        return this.component.buildEventSearchResultsScene(containerWidth, containerHeight, results);
    }

}