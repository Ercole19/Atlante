package com.example.athena;

public enum SubjectLabels
{
    COMPUTER_SCIENCE,
    CALCULUS_1,
    GEOMETRY ;

    public static String printValue(SubjectLabels enumVal) throws Exception
    {
        switch(enumVal)
        {
            case COMPUTER_SCIENCE:
                return "Computer science" ;

            case CALCULUS_1:
                return "Calculus 1" ;

            case GEOMETRY:
                return "Geometry" ;

            default :
                throw new Exception() ;
        }
    }
}
