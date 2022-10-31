package com.example.athena.entities;

import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.PlottingException;
import javafx.scene.chart.XYChart;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class PlotEntity
{
    private ActivityTypesEnum type ;
    private List<EventEntity> eventsToPlot ;
    private PlotSeries series ;

    private LocalDate startDay ;

    public PlotEntity(ActivityTypesEnum type, TimePeriodsEnum timeSpan)
    {
        setType(type) ;

        switch(timeSpan)
        {
            case LAST_WEEK:
                startDay = LocalDate.now().minusWeeks(1) ;
                break ;
            case LAST_MONTH:
                startDay = LocalDate.now().minusMonths(1) ;
                break ;
            case LAST_TWO_WEEKS:
                startDay = LocalDate.now().minusWeeks(2) ;
                break ;
            case LAST_TWO_MONTHS:
                startDay = LocalDate.now().minusMonths(2) ;
        }
    }

    private void generateSeries() throws EventException
    {
        getEventsToPlot() ;

        String name = this.type.toString() ;
        this.series = new PlotSeries(name.charAt(0) + name.substring(1).toLowerCase().replace("_" , " "), startDay) ;

        for(EventEntity event : this.eventsToPlot)
        {
            this.series.putEntry(event.getDay(), event.getSpanMinutes()) ;
        }
    }

    private void getEventsToPlot() throws EventException
    {
        this.eventsToPlot = EventEntity.getEventsByTypeSpan(this.type, this.startDay) ;
    }

    public PlotSeries getSeries() throws PlottingException
    {
        try
        {
            generateSeries() ;
            return this.series ;
        }catch (EventException e)
        {
            throw new PlottingException("Unable to create plot: failed to retrieve events\nDetails: " + e.getMessage()) ;
        }
    }

    public void setType(ActivityTypesEnum type)
    {
        this.type = type ;
    }
}
