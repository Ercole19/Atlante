package com.example.athena.entities;

import com.example.athena.exceptions.EventException;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlotEntity
{
    private ActivityTypesEnum type ;
    private TimePeriodsEnum timeSpan ;
    private List<EventEntity> eventsToPlot ;
    private XYChart.Series<String, Number> series ;

    private void generateSeries() throws EventException
    {
        this.getEventsToPlot() ;
        this.series = new XYChart.Series<>() ;


    }

    private void getEventsToPlot() throws EventException
    {
        this.eventsToPlot = EventEntity.getEvents(this.type, this.timeSpan) ;
    }

    public XYChart.Series<String, Number> getSeries()
    {
        return this.series ;
    }
}
