package com.example.athena.graphical_controller;

public class PlotSearchQueryBean
{
    private String activityType ;
    private String periodType ;

    public void setActivityType(String type)
    {
        this.activityType = type ;
    }

    public void setPeriodType(String type)
    {
        this.periodType = type ;
    }

    public String getActivityType()
    {
        return this.activityType ;
    }

    public String getPeriodType()
    {
        return this.periodType ;
    }
}
