package com.example.athena.entities;

import javafx.util.StringConverter;

public class StringHoursConverter extends StringConverter<Integer>
{
    private int minValue ;
    private int maxValue ;

    public StringHoursConverter(int minValue, int maxValue)
    {
        this.minValue = minValue ;
        this.maxValue = maxValue ;
    }

    @Override
    public String toString(Integer integer) {
        return integer.toString() ;
    }

    @Override
    public Integer fromString(String s)
    {
        try
        {
            int i = Integer.parseInt(s) ;
            if(i < minValue || i > maxValue)
            {
                return 0 ;
            }
            return  i ;
        }
        catch (NumberFormatException e)
        {
            return 0 ;
        }
    }
}
