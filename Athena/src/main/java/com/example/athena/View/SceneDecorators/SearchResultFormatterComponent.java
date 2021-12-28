package com.example.athena.View.SceneDecorators;

import com.example.athena.GraphicalController.TutorSearchResultBean;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public abstract class SearchResultFormatterComponent
{
    public abstract AnchorPane buildTutorSearchResultsScene(double containerWidth, double containerHeight, ArrayList<TutorSearchResultBean> results) ;
}
