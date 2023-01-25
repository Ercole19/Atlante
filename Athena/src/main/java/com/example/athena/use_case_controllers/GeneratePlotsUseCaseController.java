package com.example.athena.use_case_controllers;

import com.example.athena.entities.*;
import com.example.athena.exceptions.PlottingException;
import com.example.athena.beans.ActivityPlotsBean;
import com.example.athena.beans.PlotSearchQueryBean;

import java.util.ArrayList;
import java.util.List;

public class GeneratePlotsUseCaseController
{
    public List<ActivityPlotsBean> evaluateQuery(PlotSearchQueryBean searchQuery) throws PlottingException
    {
        String activityType = searchQuery.getActivityType().replace(" ", "_").toUpperCase() ;
        String period = searchQuery.getPeriodType().replace(" ", "_").toUpperCase() ;
        TimePeriodsEnum from = TimePeriodsEnum.valueOf(period) ;
        List<ActivityPlotsBean> result = new ArrayList<>() ;

        switch(PlottingOptionsEnum.valueOf(activityType))
        {
            case ALL :

                for(ActivityTypesEnum type : ActivityTypesEnum.values())
                {
                    result.add(extractFromSeries(type, from)) ;
                }
                break;

            case LECTURE_TIME:
            case STUDY_SESSION :
            case OTHER :

                ActivityTypesEnum type = ActivityTypesEnum.valueOf(activityType) ;
                result.add(extractFromSeries(type, from)) ;
                break;

            default :
                break;

        }

        return result ;
    }

    private ActivityPlotsBean extractFromSeries(ActivityTypesEnum type, TimePeriodsEnum from) throws PlottingException {

        PlotSeries series = (new PlotEntity(type, from)).getSeries() ;
        ActivityPlotsBean bean = new ActivityPlotsBean() ;
        bean.setPlotName(series.getSeriesName());
        bean.setPlotStartTime(series.getSeriesStart());

        bean.setPlotSeries(series.getEntries()) ;
        return bean ;
    }
}
