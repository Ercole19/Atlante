package com.example.athena.use_case_controllers;

import com.example.athena.entities.ExamsSubject;
import com.example.athena.entities.UserDao;
import com.example.athena.exceptions.ExamException;
import com.example.athena.graphical_controller.CareerInformationBean;
import com.example.athena.graphical_controller.ExamEntityBean;
import javafx.collections.ObservableList;

public class CareerStatusUCC {

    public CareerInformationBean getAllInfos() throws ExamException {
        CareerInformationBean careerInfos = new CareerInformationBean();
        ObservableList<ExamEntityBean> exams = ExamsSubject.getInstance().getExams();
        int totalCfus = 0;

        careerInfos.setTotalExams(this.retrieveTotalExams()) ;
        careerInfos.setTotalCfus(this.retrieveTotalCfus());
        careerInfos.setTakenExams(exams.size()) ;

        for(ExamEntityBean bean : exams)
        {
            totalCfus = totalCfus + bean.getExamCfu() ;
        }

        careerInfos.setGainedCfus(totalCfus) ;
        return careerInfos;
    }


    private int retrieveTotalExams() {
        UserDao dao = new UserDao();
        return dao.getAllExams();
    }

    private int retrieveTotalCfus() {
        UserDao dao = new UserDao();
        return dao.getAllCfus();
    }
}