package com.example.athena.use_case_controllers;

import com.example.athena.entities.ActivityTypesEnum;
import com.example.athena.graphical_controller.ActivityPlotsBean;
import com.example.athena.graphical_controller.PlotSearchQueryBean;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Calendar;

public class GeneratePlotsUseCaseController
{
    public ActivityPlotsBean evaluateQuery(PlotSearchQueryBean searchQuery)
    {
        ArrayList<XYChart.Series<String, Number>> plotsList  = new ArrayList<>() ;
        String activityType = searchQuery.getActivityType().replace(" ", "_").toUpperCase() ;


        switch(ActivityTypesEnum.valueOf(activityType))
        {
            case ALL :
                plotsList.add(retrieveSeriesLectureTime()) ;
                plotsList.add(retrieveSeriesStudySession()) ;
                plotsList.add(retrieveSeriesOther()) ;
                return new ActivityPlotsBean(plotsList) ;

            case LECTURE_TIME:
                plotsList.add(retrieveSeriesLectureTime()) ;
                return new ActivityPlotsBean(plotsList) ;

            case STUDY_SESSION:
                plotsList.add(retrieveSeriesStudySession()) ;
                return new ActivityPlotsBean(plotsList) ;

            case OTHER:
                plotsList.add(retrieveSeriesOther()) ;
                return new ActivityPlotsBean(plotsList) ;

            default :
                return new ActivityPlotsBean(plotsList) ;

        }
    }

    private XYChart.Series<String, Number> retrieveSeriesLectureTime()
    {
        XYChart.Series<String, Number> retrievedSeries = new XYChart.Series<>() ;

        retrievedSeries.setName("LectureTime") ;

        Calendar calendar = Calendar.getInstance() ;

        Number[] times = {4, 6, 6, 8, 2, 0, 0} ;

        for(int i = 0; i < 7; i++)
        {
            calendar.set(2021, 12, 20 + i) ;
            retrievedSeries.getData().add(new XYChart.Data<>(calendar.getTime().toString(), times[i])) ;
        }

        return retrievedSeries ;
    }

    private XYChart.Series<String, Number> retrieveSeriesStudySession()
    {
        XYChart.Series<String, Number> retrievedSeries = new XYChart.Series<>() ;

        retrievedSeries.setName("Study sessions") ;

        Calendar calendar = Calendar.getInstance() ;

        Number[] times = {3, 3, 2, 1, 4, 5, 6} ;

        for(int i = 0; i < 7; i++)
        {
            calendar.set(2021, 12, 20 + i) ;
            retrievedSeries.getData().add(new XYChart.Data<>(calendar.getTime().toString(), times[i])) ;
        }

        return retrievedSeries ;
    }

    private XYChart.Series<String, Number> retrieveSeriesOther()
    {
        XYChart.Series<String, Number> retrievedSeries = new XYChart.Series<>() ;

        retrievedSeries.setName("Other") ;

        Calendar calendar = Calendar.getInstance() ;

        Number[] times = {2, 2, 2, 2, 2, 2, 2} ;

        for(int i = 0; i < 7; i++)
        {
            calendar.set(2021, 12, 20 + i) ;
            retrievedSeries.getData().add(new XYChart.Data<>(calendar.getTime().toString(), times[i])) ;
        }

        return retrievedSeries ;
    }
}
