package com.example.athena.entities;

import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import com.example.athena.exceptions.EventException;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CalendarSubject extends AbstractSubject {

    private static CalendarSubject instance = null ;
    private HashMap<YearMonth, CalendarEntity> calendarMap;

    private CalendarSubject()
    {
        this.calendarMap = new HashMap<>() ;
    }

    public static synchronized CalendarSubject getInstance()
    {
        if(CalendarSubject.instance == null)
        {
            CalendarSubject.instance = new CalendarSubject() ;
        }

        return CalendarSubject.instance ;
    }

    public CalendarEntity getEntity(YearMonth yearMonth) throws EventException {

        this.calendarMap.putIfAbsent(yearMonth, new CalendarEntity(yearMonth)) ;
        return this.calendarMap.get(yearMonth) ;
    }

    public void addEvent(EventEntity event) throws EventException
    {
        this.getEntity(YearMonth.of(event.getDay().getYear(), event.getDay().getMonth())).addEvent(event) ;
    }

    public void deleteRow(GridPane grid, final int row) {
        Set<Node> deleteNodes = new HashSet<>();
        for (Node child : grid.getChildren()) {
            // get index from child
            Integer rowIndex = GridPane.getRowIndex(child);

            // handle null values for index=0
            int r = rowIndex == null ? 0 : rowIndex;

            if (r > row) {
                // decrement rows for rows after the deleted row
                GridPane.setRowIndex(child, r-1);
            } else if (r == row) {
                // collect matching rows for deletion
                deleteNodes.add(child);
            }
        }

        // remove nodes from row
        grid.getChildren().removeAll(deleteNodes);
    }
}
