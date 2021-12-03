package com.example.athena;

import java.util.ArrayList;

public class SearchTutorUseCaseController
{
    public ArrayList<TutorSearchResultEntity> formatSearchResults(String query)
    {
        ArrayList<TutorSearchResultEntity> result = new ArrayList<>();

        if(!query.isEmpty())
        {
            TutorSearchResultEntity kanye = new TutorSearchResultEntity("Kanye", "East", "Rap History", 3.5f);
            TutorSearchResultEntity btuni = new TutorSearchResultEntity("Carlo", "Btuni", "Lettere", 4.0f);

            result.add(kanye);
            result.add(btuni);
        }

        return result ;
    }
}
