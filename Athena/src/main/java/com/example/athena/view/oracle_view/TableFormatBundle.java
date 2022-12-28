package com.example.athena.view.oracle_view;

import com.example.athena.exceptions.PercentFormatException;
import com.example.athena.exceptions.WrongIndexNumberException;

import java.util.ArrayList;
import java.util.List;

public class TableFormatBundle {

    private double width ;
    private double height ;
    private int rows ;
    private int cols ;

    private double containerWidth ;

    private double containerHeight ;

    private List<Integer> horizontalPercents ;
    private List<Integer> verticalPercents ;

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

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public List<Integer> getHorizontalPercents() {
        return horizontalPercents;
    }

    public void setHorizontalPercents(int ... percents) throws PercentFormatException, WrongIndexNumberException {

        if(percents.length != cols) throw new WrongIndexNumberException("Not enough percents") ;

        int sum = 0 ;
        for(int i : percents) {
            sum = sum+i ;
        }

        if(sum != 100) throw new PercentFormatException("The percents don't add up to 100") ;
        else {
            ArrayList<Integer> list = new ArrayList<>() ;
            for (int i : percents) {
                list.add(i) ;
            }

            this.horizontalPercents = list ;
        }
    }

    public List<Integer> getVerticalPercents() {
        return verticalPercents;
    }

    public void setVerticalPercents(int ... percents) throws PercentFormatException, WrongIndexNumberException {

        if(percents.length != rows) throw new WrongIndexNumberException("Not enough percents") ;

        int sum = 0 ;
        for(int i : percents) {
            sum = sum+i ;
        }

        if(sum != 100) throw new PercentFormatException("The percents don't add up to 100") ;
        else {
            ArrayList<Integer> list = new ArrayList<>() ;
            for (int i : percents) {
                list.add(i) ;
            }

            this.verticalPercents = list ;
        }
    }

    public double getContainerWidth() {
        return containerWidth;
    }

    public void setContainerWidth(double containerWidth) {
        this.containerWidth = containerWidth;
    }

    public double getContainerHeight() {
        return containerHeight;
    }

    public void setContainerHeight(double containerHeight) {
        this.containerHeight = containerHeight;
    }
}
