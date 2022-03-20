package com.example.athena.use_case_controllers;

import com.example.athena.entities.ExamsSubject;
import com.example.athena.exceptions.ExamException;
import com.example.athena.graphical_controller.ExamAverageInformation;
import com.example.athena.graphical_controller.ExamEntityBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AverageUCC  {

    public ObservableList<ExamAverageInformation> getExamsArithmeticAverageInformation() throws ExamException{

        ObservableList<ExamEntityBean> exams = ExamsSubject.getInstance().getSortedExams();
        ObservableList<ExamAverageInformation> examsArithmeticAverageInfos = FXCollections.observableArrayList();
        int count = 1;
        double average ;
        double  gradesCounter = 0.0 ;
        String date ;
        int grade ;

        for(ExamEntityBean exam : exams)
        {
            date = exam.getExamDate();
            grade = exam.getExamGrade();
            gradesCounter = gradesCounter + grade;
            average = (gradesCounter) / count;

            ExamAverageInformation info = new ExamAverageInformation();
            info.setAverage(average);
            info.setDate(date);

            examsArithmeticAverageInfos.add(info);
            count++;

        }

        return examsArithmeticAverageInfos;
    }

    public ObservableList<ExamAverageInformation> getExamsWeightedAverageInformation() throws ExamException {

        ObservableList<ExamEntityBean> exams = ExamsSubject.getInstance().getSortedExams() ;
        ObservableList<ExamAverageInformation> examsWeightedAverageInfos = FXCollections.observableArrayList();
        int  cfus = 0 ;
        double average ;
        double gradesCounter = 0.0;
        int grade ;
        int cfu ;

        for(ExamEntityBean exam : exams)
        {
            String date = exam.getExamDate();
            grade = exam.getExamGrade();
            cfu = exam.getExamCfu();
            cfus = cfus + cfu ;
            gradesCounter = gradesCounter + (grade * cfu );

            average = (gradesCounter) / cfus;

            ExamAverageInformation info = new ExamAverageInformation();
            info.setAverage(average);
            info.setDate(date);

            examsWeightedAverageInfos.add(info);
        }

        return examsWeightedAverageInfos;
    }
}
