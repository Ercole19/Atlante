package com.example.athena.use_case_controllers;

import com.example.athena.beans.OutputExamBean;
import com.example.athena.entities.ExamsSubject;
import com.example.athena.exceptions.ExamException;
import com.example.athena.beans.ExamAverageInformationBean;
import com.example.athena.exceptions.UserInfoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AverageUCC  {

    public ObservableList<ExamAverageInformationBean> getExamsArithmeticAverageInformation() throws ExamException, UserInfoException {

        ObservableList<OutputExamBean> exams = ExamsSubject.getInstance().getSortedExams();
        ObservableList<ExamAverageInformationBean> examsArithmeticAverageInfos = FXCollections.observableArrayList();
        int count = 1;
        double average ;
        double  gradesCounter = 0.0 ;
        String date ;
        int grade ;

        for(OutputExamBean exam : exams)
        {
            date = exam.getExamDate();
            grade = exam.getExamGrade();
            gradesCounter = gradesCounter + grade;
            average = (gradesCounter) / count;

            ExamAverageInformationBean info = new ExamAverageInformationBean();
            info.setAverage(average);
            info.setDate(date);

            examsArithmeticAverageInfos.add(info);
            count++;

        }

        return examsArithmeticAverageInfos;
    }

    public ObservableList<ExamAverageInformationBean> getExamsWeightedAverageInformation() throws ExamException, UserInfoException {

        ObservableList<OutputExamBean> exams = ExamsSubject.getInstance().getSortedExams() ;
        ObservableList<ExamAverageInformationBean> examsWeightedAverageInfos = FXCollections.observableArrayList();
        int  cfus = 0 ;
        double average ;
        double gradesCounter = 0.0;
        int grade ;
        int cfu ;

        for(OutputExamBean exam : exams)
        {
            String date = exam.getExamDate();
            grade = exam.getExamGrade();
            cfu = exam.getExamCfu();
            cfus = cfus + cfu ;
            gradesCounter = gradesCounter + (grade * cfu );

            average = (gradesCounter) / cfus;

            ExamAverageInformationBean info = new ExamAverageInformationBean();
            info.setAverage(average);
            info.setDate(date);

            examsWeightedAverageInfos.add(info);
        }

        return examsWeightedAverageInfos;
    }
}
