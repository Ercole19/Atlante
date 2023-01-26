package com.example.athena.entities;

import com.example.athena.dao.TutorSearchResultDAO;
import com.example.athena.exceptions.FindException;

import java.util.List;

public class TutorSearchResult {

    private String id ;
    private String name ;
    private String surname ;
    private String taughtSubject ;
    private float starNumber ;

    public TutorSearchResult(String id, String name, String surname, String taughtSubject, float starNumber) {
        this.id = id ;
        this.name = name ;
        this.surname = surname ;
        this.taughtSubject = taughtSubject ;
        this.starNumber = starNumber ;
    }

    public static List<TutorSearchResult> getFromQuery(String query, ByCourseOrNameEnum searchEnum, boolean byBestReviews) throws FindException {
        return new TutorSearchResultDAO().findTutor(query, searchEnum, byBestReviews) ;
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

    public float getStarNumber()
    {
        return this.starNumber ;
    }

    public String getId() { return this.id ;}
}
