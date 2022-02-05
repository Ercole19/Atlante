package com.example.athena.use_case_controllers;

import com.example.athena.entities.ActivityTypesEnum;
import com.example.athena.entities.PlotEntity;
import com.example.athena.entities.PlottingOptionsEnum;
import com.example.athena.entities.TimePeriodsEnum;
import com.example.athena.exceptions.PlottingException;
import com.example.athena.graphical_controller.ActivityPlotsBean;
import com.example.athena.graphical_controller.PlotSearchQueryBean;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class GeneratePlotsUseCaseController
{
    public ActivityPlotsBean evaluateQuery(PlotSearchQueryBean searchQuery) throws PlottingException
    {
        ArrayList<XYChart.Series<String, Long>> plotsList  = new ArrayList<>() ;
        String activityType = searchQuery.getActivityType().replace(" ", "_").toUpperCase() ;
        String period = searchQuery.getPeriodType().replace(" ", "_").toUpperCase() ;
        TimePeriodsEnum from = TimePeriodsEnum.valueOf(period) ;

        switch(PlottingOptionsEnum.valueOf(activityType))
        {
            case ALL :
                for(ActivityTypesEnum type : ActivityTypesEnum.values())
                {
                    plotsList.add(retrieveSeries(type, from)) ;
                }
                return new ActivityPlotsBean(plotsList) ;

            case LECTURE_TIME:
            case STUDY_SESSION :
            case OTHER :

                ActivityTypesEnum type = ActivityTypesEnum.valueOf(activityType) ;
                plotsList.add(retrieveSeries(type, from)) ;
                return new ActivityPlotsBean(plotsList) ;

            default :
                return new ActivityPlotsBean(plotsList) ;

        }
    }

    private XYChart.Series<String, Long> retrieveSeries(ActivityTypesEnum type, TimePeriodsEnum from) throws PlottingException
    {
        return (new PlotEntity(type, from)).getSeries() ;
    }
}
