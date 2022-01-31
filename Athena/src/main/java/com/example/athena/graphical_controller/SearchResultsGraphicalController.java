package com.example.athena.graphical_controller;

import javafx.scene.layout.VBox;

public class SearchResultsGraphicalController
{
    public void scrollResults(VBox resultList, Number newVal)
    {
        resultList.setLayoutY(-newVal.doubleValue()) ;
    }
}
