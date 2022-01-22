package com.example.athena.GraphicalController;

public class TutorSearchResultBean
{
    private String id ;
    private final String name ;
    private final String surname ;
    private final String taughtSubject ;
    private final String starNumber ;

    public TutorSearchResultBean(String name, String surname, String taughtSubject, float starNumber, String id)
    {
        this.name = name ;
        this.surname = surname ;
        this.taughtSubject = taughtSubject ;
        this.starNumber = String.format("%f*", starNumber) ;
        this.id = id ;
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

    public String getId() { return this.id ;}
}
