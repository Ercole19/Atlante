package com.example.athena.engineering_classes.search_result_factory;

import com.example.athena.exceptions.PercentFormatException;

import java.util.ArrayList;
import java.util.List;

public class FormatBundle {
    private double containerWidth ;
    private double containerHeight ;
    private int entryNumber ;
    private double entrySize ;

    private double width ;

    private double height ;

    private List<Integer> entryPercents = new ArrayList<>() ;


    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber ;
    }

    public void setEntryPercents(int ... percent) throws PercentFormatException
    {
        int sum = 0;
        for (int i : percent){
            this.entryPercents.add(i);
            sum  = sum + i;
        }
        if (sum != 100 )
        {
            this.entryPercents.clear() ;
            throw new PercentFormatException("Sum of integers passed must be 100");
        }
    }

    public int getEntryNumber() {
        return entryNumber;
    }

    public List<Integer> getEntryPercents() {
        return entryPercents;
    }

    public double getContainerHeight() {
        return containerHeight;
    }

    public double getContainerWidth() {
        return containerWidth;
    }

    public void setContainerHeight(double containerHeight) {
        this.containerHeight = containerHeight;
    }

    public void setEntrySize(double entrySize) {
        this.entrySize = entrySize;
    }

    public double getEntrySize() {
        return this.entrySize;
    }
    public void setContainerWidth(double containerWidth) {
        this.containerWidth = containerWidth;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
