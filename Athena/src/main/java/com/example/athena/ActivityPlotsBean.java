package com.example.athena;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class ActivityPlotsBean
{
    private ArrayList<XYChart.Series<String, Number>> activityPlots ;

    public ActivityPlotsBean(ArrayList<XYChart.Series<String, Number>> activityPlots)
    {
        this.setActivityPlots(activityPlots) ;
    }

    public void setActivityPlots(ArrayList<XYChart.Series<String, Number>> activityPlots)
    {
        this.activityPlots = activityPlots ;
    }

    public ArrayList<XYChart.Series<String, Number>> getActivityPlots()
    {
        return this.activityPlots ;
    }
}
