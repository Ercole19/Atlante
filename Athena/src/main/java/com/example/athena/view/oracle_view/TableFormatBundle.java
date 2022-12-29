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

    private List<Double> horizontalPercents ;
    private List<Double> verticalPercents ;

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

    public List<Double> getHorizontalPercents() {
        return horizontalPercents;
    }

    private List<Double> checkPercents(int size, double ... percents) throws PercentFormatException, WrongIndexNumberException{
        if(percents.length != size) throw new WrongIndexNumberException("Not enough percents") ;

        double sum = 0 ;
        for(double i : percents) {
            sum = sum+i ;
        }

        if(sum > 101.0) throw new PercentFormatException("The percents don't add up to 100") ;
        else {
            ArrayList<Double> list = new ArrayList<>() ;
            for (double i : percents) {
                list.add(i) ;
            }

            return list ;
        }
    }

    public void setHorizontalPercents(double ... percents) throws PercentFormatException, WrongIndexNumberException {

        this.horizontalPercents = checkPercents(cols, percents) ;
    }

    public List<Double> getVerticalPercents() {
        return verticalPercents;
    }

    public void setVerticalPercents(double ... percents) throws PercentFormatException, WrongIndexNumberException {

        this.verticalPercents = checkPercents(rows, percents) ;
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
