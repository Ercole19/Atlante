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
import java.util.List;

public class GeneratePlotsUseCaseController
{
    private final ActivityPlotsBean resultBean = new ActivityPlotsBean() ;

    public ActivityPlotsBean evaluateQuery(PlotSearchQueryBean searchQuery) throws PlottingException
    {
        List<XYChart.Series<String, Long>> plotsList  = new ArrayList<>() ;
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

                resultBean.setActivityPlots(plotsList) ;
                return resultBean ;

            case LECTURE_TIME:
            case STUDY_SESSION :
            case OTHER :

                ActivityTypesEnum type = ActivityTypesEnum.valueOf(activityType) ;
                plotsList.add(retrieveSeries(type, from)) ;
                resultBean.setActivityPlots(plotsList) ;
                return resultBean ;

            default :
                resultBean.setActivityPlots(plotsList) ;
                return resultBean ;

        }
    }

    private XYChart.Series<String, Long> retrieveSeries(ActivityTypesEnum type, TimePeriodsEnum from) throws PlottingException
    {
        return (new PlotEntity(type, from)).getSeries() ;
    }
}
