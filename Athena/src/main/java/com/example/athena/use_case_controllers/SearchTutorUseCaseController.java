package com.example.athena.use_case_controllers;

import com.example.athena.beans.SearchTutorQueryBean;
import com.example.athena.beans.TutorSearchResultBean;
import com.example.athena.entities.ByCourseOrNameEnum;
import com.example.athena.entities.TutorSearchResult;
import com.example.athena.exceptions.FindException;
import com.example.athena.exceptions.FindTutorException;

import java.util.ArrayList;
import java.util.List;

public class SearchTutorUseCaseController {

    public List<TutorSearchResultBean> formatSearchResults(SearchTutorQueryBean bean) throws FindTutorException {

        List<TutorSearchResult> tutorinfos;
        try {
            ByCourseOrNameEnum searchEnum = ByCourseOrNameEnum.valueOf(bean.getSearchType()) ;
            tutorinfos = TutorSearchResult.getFromQuery(bean.getQuery(), searchEnum, bean.isBybestreviews()) ;
        } catch (FindException | IllegalArgumentException e) {
            throw new FindTutorException(e.getMessage());
        }

        return convertSearchResults(tutorinfos) ;
    }

    private List<TutorSearchResultBean> convertSearchResults(List<TutorSearchResult> results) {
        List<TutorSearchResultBean> convertedResults = new ArrayList<>() ;

        for(TutorSearchResult result : results) {
            TutorSearchResultBean bean = new TutorSearchResultBean() ;
            bean.setId(result.getId()) ;
            bean.setName(result.getName()) ;
            bean.setSurname(result.getSurname());
            bean.setStarNumber(result.getStarNumber());
            bean.setTaughtSubject(result.getTaughtSubject()) ;
            convertedResults.add(bean) ;
        }

        return convertedResults ;
    }


}
