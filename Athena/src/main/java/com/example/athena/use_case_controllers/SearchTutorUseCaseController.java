package com.example.athena.use_case_controllers;

import com.example.athena.graphical_controller.TutorSearchResultBean;
import com.example.athena.entities.UserDao;

import java.util.ArrayList;

public class SearchTutorUseCaseController
{
    UserDao user = new UserDao() ;

    public ArrayList<TutorSearchResultBean> formatSearchResultsByCourse(String query)
    {
        int i = 0 ;
        String[] tutorinfos = user.findTutorByCourse(query) ;
        ArrayList<TutorSearchResultBean> result = new ArrayList<>();
        while (tutorinfos[i] != null) {

            TutorSearchResultBean kanye = new TutorSearchResultBean(tutorinfos[i], tutorinfos[i+1], tutorinfos[i+2], Float.parseFloat(tutorinfos[i+3]) , tutorinfos[i+4]);
            result.add(kanye);
            i = i + 5;
        }



        return result ;
    }

    public ArrayList<TutorSearchResultBean> formatSearchResultsByName(String query)
    {
        int i = 0 ;
        String[] tutorinfos = user.findTutorByName(query) ;
        ArrayList<TutorSearchResultBean> result = new ArrayList<>();
        while (tutorinfos[i] != null) {

            TutorSearchResultBean kanye = new TutorSearchResultBean(tutorinfos[i], tutorinfos[i+1], tutorinfos[i+2], Float.parseFloat(tutorinfos[i+3]), tutorinfos[i+4]);
            result.add(kanye);
            i = i + 5 ;
        }



        return result ;
    }
}
