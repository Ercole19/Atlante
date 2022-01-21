package com.example.athena.UseCaseControllers;

import com.example.athena.GraphicalController.TutorSearchResultBean;
import com.example.athena.View.userdao;
import com.example.athena.View.userdao ;

import java.util.ArrayList;

public class SearchTutorUseCaseController
{
    userdao user = new userdao() ;

    public ArrayList<TutorSearchResultBean> formatSearchResultsByCourse(String query)
    {
        int i = 0 ;
        String[] tutorinfos = user.findTutorByCourse(query) ;
        ArrayList<TutorSearchResultBean> result = new ArrayList<>();
        do {

            TutorSearchResultBean kanye = new TutorSearchResultBean(tutorinfos[i], tutorinfos[i+1], tutorinfos[i+2], 3.5f);
            result.add(kanye);
            i = i + 3 ;
        }while (tutorinfos[i] != null) ;



        return result ;
    }
    public ArrayList<TutorSearchResultBean> formatSearchResultsByName(String query)
    {
        int i = 0 ;
        String[] tutorinfos = user.findTutorByName(query) ;
        ArrayList<TutorSearchResultBean> result = new ArrayList<>();
        do {

            TutorSearchResultBean kanye = new TutorSearchResultBean(tutorinfos[i], tutorinfos[i+1], tutorinfos[i+2], 3.5f);
            result.add(kanye);
            i = i + 3 ;
        }while (tutorinfos[i] != null) ;



        return result ;
    }
}
