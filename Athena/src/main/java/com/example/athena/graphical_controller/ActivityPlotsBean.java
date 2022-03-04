package com.example.athena.graphical_controller;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class ActivityPlotsBean
{
    private List<XYChart.Series<String, Long>> activityPlots ;

    public void setActivityPlots(List<XYChart.Series<String, Long>> activityPlots)
    {
        this.activityPlots = activityPlots ;
    }

    public List<XYChart.Series<String, Long>> getActivityPlots()
    {
        return this.activityPlots ;
    }
}
