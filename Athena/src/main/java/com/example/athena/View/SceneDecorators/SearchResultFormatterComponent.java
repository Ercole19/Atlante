package com.example.athena.View.SceneDecorators;

import com.example.athena.GraphicalController.TutorSearchResultBean;
import com.example.athena.GraphicalController.eventBean;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public abstract class SearchResultFormatterComponent
{
    public abstract AnchorPane buildEventSearchResultsScene(double containerWidth, double containerHeight, ArrayList<eventBean> results) ;
    public abstract AnchorPane buildTutorSearchResultsScene(double containerWidth, double containerHeight, ArrayList<TutorSearchResultBean> results);
}


