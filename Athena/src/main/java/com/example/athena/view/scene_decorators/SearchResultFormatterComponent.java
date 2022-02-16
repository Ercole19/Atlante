package com.example.athena.view.scene_decorators;

import com.example.athena.graphical_controller.EventBean;
import com.example.athena.graphical_controller.TutorSearchResultBean;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public abstract class SearchResultFormatterComponent
{
    public abstract AnchorPane buildEventSearchResultsScene(double containerWidth, double containerHeight, ArrayList<EventBean> results) ;
    public abstract AnchorPane buildTutorSearchResultsScene(double containerWidth, double containerHeight, ArrayList<TutorSearchResultBean> results);
}


