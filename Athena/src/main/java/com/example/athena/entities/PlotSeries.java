package com.example.athena.entities;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PlotSeries {
    private final Map<LocalDate, Long> entries = new HashMap<>();
    private final String seriesName ;
    private final LocalDate seriesStart ;

    public PlotSeries(String seriesName, LocalDate seriesStart) {
        this.seriesName = seriesName ;
        this.seriesStart = seriesStart ;
    }

    public LocalDate getSeriesStart() {
        return seriesStart;
    }

    public void putEntry(LocalDate entryName, Long entryValue) {
        Long value = this.entries.getOrDefault(entryName, 0L) ;
        this.entries.put(entryName, entryValue + value) ;
    }

    public Long removeEntry(LocalDate entryName) {
        return this.entries.remove(entryName) ;
    }

    public Long getEntry(LocalDate entryName) {
        return this.entries.getOrDefault(entryName, 0L) ;
    }

    public String getSeriesName() {
        return this.seriesName ;
    }

    public Map<LocalDate, Long> getEntries() {
        return this.entries ;
    }

}
