package com.example.athena.GraphicalController;

import javafx.scene.layout.VBox;

public class SearchResultsGraphicalController
{
    public void scrollResults(VBox resultList, Number newVal)
    {
        resultList.setLayoutY(-newVal.doubleValue()) ;
    }
}
