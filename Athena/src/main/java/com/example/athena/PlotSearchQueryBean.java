package com.example.athena;

public class PlotSearchQueryBean
{
    private String activityType ;
    private String periodType ;

    public PlotSearchQueryBean(String activityType, String periodType)
    {
        this.setActivityType(activityType) ;
        this.setPeriodType(periodType) ;
    }

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

    public String getPeriodTypeType()
    {
        return this.periodType ;
    }
}
