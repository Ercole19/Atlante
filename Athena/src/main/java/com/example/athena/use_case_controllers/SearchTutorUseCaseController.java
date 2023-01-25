package com.example.athena.use_case_controllers;

import com.example.athena.beans.SearchTutorQueryBean;
import com.example.athena.beans.TutorSearchResultBean;
import com.example.athena.dao.UserDao;
import com.example.athena.entities.ByCourseOrNameEnum;
import com.example.athena.exceptions.FindException;
import com.example.athena.exceptions.FindTutorException;

import java.util.ArrayList;
import java.util.List;

public class SearchTutorUseCaseController {
    UserDao user = new UserDao();

    public List<TutorSearchResultBean> formatSearchResults(SearchTutorQueryBean bean) throws FindTutorException {
        int i = 0;
        String[] tutorinfos;
        try {
            ByCourseOrNameEnum searchEnum = ByCourseOrNameEnum.valueOf(bean.getSearchType()) ;
            tutorinfos = user.findTutor(bean.getQuery(), searchEnum, bean.isBybestreviews());
        } catch (FindException | IllegalArgumentException e) {
            throw new FindTutorException(e.getMessage());
        }
        ArrayList<TutorSearchResultBean> result = new ArrayList<>();
        while (tutorinfos[i] != null) {

            TutorSearchResultBean kanye = new TutorSearchResultBean();
            kanye.setName(tutorinfos[i]);
            kanye.setSurname(tutorinfos[i + 1]);
            kanye.setTaughtSubject(tutorinfos[i + 2]);
            kanye.setStarNumber(Float.parseFloat(tutorinfos[i + 3]));
            kanye.setId(tutorinfos[i + 4]);
            result.add(kanye);
            i = i + 5;
        }


        return result;
    }


}
