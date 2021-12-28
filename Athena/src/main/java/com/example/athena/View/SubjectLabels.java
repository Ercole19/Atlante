package com.example.athena.View;

import java.util.ArrayList;

public enum SubjectLabels
{
    COMPUTER_SCIENCE,
    CALCULUS_1,
    GEOMETRY ;

    public static String printValue(SubjectLabels enumVal)
    {
        switch(enumVal)
        {
            case COMPUTER_SCIENCE:
                return "Computer science" ;

            case CALCULUS_1:
                return "Calculus 1" ;

            case GEOMETRY:
                return "Geometry" ;

            default:
                return "" ;
        }
    }

    public static ArrayList<String> getAllLabels()
    {
        ArrayList<String> retVal = new ArrayList<>() ;

        for(SubjectLabels label: SubjectLabels.values())
        {
            retVal.add(SubjectLabels.printValue(label)) ;
        }

        return retVal ;
    }
}
