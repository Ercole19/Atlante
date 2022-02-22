package com.example.athena.use_case_controllers;

import com.example.athena.entities.UserDao;
import com.example.athena.graphical_controller.TutorSearchResultBean;

import java.util.ArrayList;
import java.util.List;

public class SearchTutorUseCaseController {
    UserDao user = new UserDao();

    public List<TutorSearchResultBean> formatSearchResults(String query, boolean condition, boolean byBestReviews) {
        int i = 0;
        String[] tutorinfos = user.findTutor(query, condition, byBestReviews);
        ArrayList<TutorSearchResultBean> result = new ArrayList<>();
        while (tutorinfos[i] != null) {

            TutorSearchResultBean kanye = new TutorSearchResultBean(tutorinfos[i], tutorinfos[i + 1], tutorinfos[i + 2], Float.parseFloat(tutorinfos[i + 3]), tutorinfos[i + 4]);
            result.add(kanye);
            i = i + 5;
        }


        return result;
    }


}
