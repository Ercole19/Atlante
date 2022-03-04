package com.example.athena.graphical_controller;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class ActivityPlotsBean
{
    private ArrayList<XYChart.Series<String, Long>> activityPlots ;

    public ActivityPlotsBean(ArrayList<XYChart.Series<String, Long>> activityPlots)
    {
        this.setActivityPlots(activityPlots) ;
    }

    public void setActivityPlots(ArrayList<XYChart.Series<String, Long>> activityPlots)
    {
        this.activityPlots = activityPlots ;
    }

    public ArrayList<XYChart.Series<String, Long>> getActivityPlots()
    {
        return this.activityPlots ;
    }
}
