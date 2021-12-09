package com.example.athena;

import javafx.scene.layout.VBox;

public class SearchResultsGraphicalController
{
    public void scrollResults(VBox resultList, Number newVal)
    {
        resultList.setLayoutY(-newVal.doubleValue()) ;
    }
}
