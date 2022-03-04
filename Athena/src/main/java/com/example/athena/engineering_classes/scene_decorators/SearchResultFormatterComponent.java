package com.example.athena.engineering_classes.scene_decorators;

import com.example.athena.graphical_controller.BookBean;
import com.example.athena.graphical_controller.BookSearchResultBean;
import com.example.athena.graphical_controller.EventBean;
import com.example.athena.graphical_controller.TutorSearchResultBean;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchResultFormatterComponent
{
    public abstract AnchorPane buildEventSearchResultsScene(double containerWidth, double containerHeight, List<EventBean> results) ;
    public abstract AnchorPane buildTutorSearchResultsScene(double containerWidth, double containerHeight, List<TutorSearchResultBean> results);
    public abstract AnchorPane buildBookSearchResultsScene(double containerWidth, double containerHeight, List<BookSearchResultBean> results);
    public abstract AnchorPane buildRecentPurchaseResultScene(double containerWidth, double containerHeight, List<BookBean> results);
}


