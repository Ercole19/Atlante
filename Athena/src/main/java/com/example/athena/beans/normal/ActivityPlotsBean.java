package com.example.athena.beans.normal;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class ActivityPlotsBean
{
    private String plotName ;
    private LocalDate plotStartTime ;
    private Map<LocalDate, Long> plotSeries ;

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public void setPlotSeries(Map<LocalDate, Long> plotSeries) {
        this.plotSeries = plotSeries;
    }

    public String getPlotName() {
        return plotName;
    }

    public Map<LocalDate, Long> getPlotSeries() {
        return this.plotSeries ;
    }

    public void setPlotStartTime(LocalDate plotStartTime) {
        this.plotStartTime = plotStartTime;
    }

    public LocalDate getPlotStartTime() {
        return plotStartTime;
    }
}
