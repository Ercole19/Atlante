package com.example.athena;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class SearchResultFormatterScrollBar extends  SearchResultFormatterDecorator
{
    public SearchResultFormatterScrollBar(SearchResultFormatterComponent component) {
        super(component);
    }

    @Override
    public AnchorPane buildTutorSearchResultsScene(double containerWidth, double containerHeight, ArrayList<TutorSearchResultEntity> results)
    {
        AnchorPane resultPane = super.buildTutorSearchResultsScene(containerWidth, containerHeight, results) ;

        return resultPane ;
    }
}
