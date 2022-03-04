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
    private TimePeriodsEnum timeSpan ;
    private List<EventEntity> eventsToPlot ;
    private XYChart.Series<String, Long> series ;

    public PlotEntity(ActivityTypesEnum type, TimePeriodsEnum timeSpan)
    {
        setType(type) ;
        setTimeSpan(timeSpan) ;
    }

    private void generateSeries() throws EventException
    {
        HashMap<LocalDate, Long> map = new HashMap<>() ;

        getEventsToPlot() ;

        if(this.eventsToPlot.isEmpty())
        {
            this.series = new XYChart.Series<>() ;
            String name = this.type.toString() ;
            this.series.setName(name.charAt(0) + name.substring(1).toLowerCase().replace("_" , " "));
            return ;
        }

        LocalDate startDay = this.eventsToPlot.get(0).getDay();

        for(EventEntity event : this.eventsToPlot)
        {
            if(!map.containsKey(event.getDay()))
            {
                map.put(event.getDay(), event.getSpanMinutes()) ;
            }
            else
            {
                Long lastSpan = map.get(event.getDay()) ;
                map.replace(event.getDay(), event.getSpanMinutes() + lastSpan) ;
            }
        }

        XYChart.Series<String, Long> newSeries = new XYChart.Series<>() ;
        String name = this.type.toString() ;
        newSeries.setName(name.charAt(0) + name.substring(1).toLowerCase().replace("_" , " "));
        LocalDate today = LocalDate.now() ;

        while(startDay.isBefore(today))
        {
            newSeries.getData().add(new XYChart.Data<>(startDay.toString(), map.getOrDefault(startDay, 0L))) ;
            startDay = startDay.plusDays(1) ;
        }

        this.series = newSeries ;
    }

    private void getEventsToPlot() throws EventException
    {
        this.eventsToPlot = EventEntity.getEventsByTypeSpan(this.type, this.timeSpan) ;
    }

    public XYChart.Series<String, Long> getSeries() throws PlottingException
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

    public void setTimeSpan(TimePeriodsEnum span)
    {
        this.timeSpan = span ;
    }
}
