package com.example.athena.graphical_controller;

public class TutorSearchResultBean
{
    private String id ;
    private String name ;
    private String surname ;
    private String taughtSubject ;
    private String starNumber ;

    public void setName(String name){
        this.name = name;
    }

    public void setTaughtSubject(String taughtSubject){
        this.taughtSubject = taughtSubject;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setStarNumber(float starNumber) {
        this.starNumber = String.format("%.2f*", starNumber);
    }

    public void setId(String id){
        this.id = id;
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
