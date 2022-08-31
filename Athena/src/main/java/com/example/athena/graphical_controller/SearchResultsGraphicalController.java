package com.example.athena.graphical_controller;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchResultsGraphicalController
{
    public void scrollResultsVertical(VBox resultList, Number newVal)
    {
        resultList.setLayoutY(-newVal.doubleValue()) ;
    }

    public void scrollResultsHorizontal(HBox resultList, Number newVal)
    {
        resultList.setLayoutX(-newVal.doubleValue());
    }
}
