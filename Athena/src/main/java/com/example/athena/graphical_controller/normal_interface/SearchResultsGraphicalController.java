package com.example.athena.graphical_controller.normal_interface;

import javafx.scene.layout.Pane;

public class SearchResultsGraphicalController
{
    public void scrollResultsVertical(Pane resultList, Number newVal)
    {
        resultList.setLayoutY(-newVal.doubleValue()) ;
    }

    public void scrollResultsHorizontal(Pane resultList, Number newVal)
    {
        resultList.setLayoutX(-newVal.doubleValue());
    }
}
