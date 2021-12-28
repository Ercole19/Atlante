package com.example.athena.UseCaseControllers;

import com.example.athena.GraphicalController.TutorSearchResultBean;

import java.util.ArrayList;

public class SearchTutorUseCaseController
{
    public ArrayList<TutorSearchResultBean> formatSearchResults(String query)
    {
        ArrayList<TutorSearchResultBean> result = new ArrayList<>();

        if(!query.isEmpty())
        {
            if(query.contentEquals("kanye"))
            {
                for(int i = 0; i < 10; i++)
                {
                    TutorSearchResultBean kanye = new TutorSearchResultBean("Kanye", "East", "Rap History", 3.5f);
                    result.add(kanye);
                }
            }
            else {
                TutorSearchResultBean kanye = new TutorSearchResultBean("Kanye", "East", "Rap History", 3.5f);
                TutorSearchResultBean btuni = new TutorSearchResultBean("Carlo", "Btuni", "Lettere", 4.0f);

                result.add(kanye);
                result.add(btuni);
            }
        }

        return result ;
    }
}
