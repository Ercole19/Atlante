package com.example.athena.view.scene_decorators;

import com.example.athena.graphical_controller.TutorSearchResultBean;
import com.example.athena.graphical_controller.eventBean;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public abstract class SearchResultFormatterComponent
{
    public abstract AnchorPane buildEventSearchResultsScene(double containerWidth, double containerHeight, ArrayList<eventBean> results) ;
    public abstract AnchorPane buildTutorSearchResultsScene(double containerWidth, double containerHeight, ArrayList<TutorSearchResultBean> results);
}


