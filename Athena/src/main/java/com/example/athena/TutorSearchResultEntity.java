package com.example.athena;

public class TutorSearchResultEntity
{
    private final String name ;
    private final String surname ;
    private final String taughtSubject ;
    private final String starNumber ;

    public TutorSearchResultEntity(String name, String surname, String taughtSubject, float starNumber)
    {
        this.name = name ;
        this.surname = surname ;
        this.taughtSubject = taughtSubject ;
        this.starNumber = String.format("%f*", starNumber) ;
    }

    public String getName()
    {
        return this.name ;
    }

    public String getSurname()
    {
        return this.surname ;
    }

    public String getTaughtSubject()
    {
        return this.taughtSubject ;
    }

    public String getStarNumber()
    {
        return this.starNumber ;
    }
}
