package com.example.athena.View.SceneDecorators;

import com.example.athena.GraphicalController.TutorSearchResultBean;
import com.example.athena.GraphicalController.eventBean;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public abstract class SearchResultFormatterDecorator extends SearchResultFormatterComponent
{
    private SearchResultFormatterComponent component ;

    public SearchResultFormatterDecorator(SearchResultFormatterComponent component)
    {
        this.component = component ;
    }

    @Override
    public AnchorPane buildTutorSearchResultsScene(double containerWidth, double containerHeight, ArrayList<TutorSearchResultBean> results)
    {
        return this.component.buildTutorSearchResultsScene(containerWidth, containerHeight, results);
    }


    public AnchorPane buildEventSearchResultsScene(double containerWidth, double containerHeight, ArrayList<eventBean> results)
    {
        return this.component.buildEventSearchResultsScene(containerWidth, containerHeight, results);
    }



}
