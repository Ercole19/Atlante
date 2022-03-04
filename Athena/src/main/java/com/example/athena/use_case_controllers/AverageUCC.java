package com.example.athena.use_case_controllers;

import com.example.athena.entities.ExamDao;
import com.example.athena.entities.ExamsSubject;
import com.example.athena.exceptions.ExamException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.graphical_controller.ExamAverageInformation;
import com.example.athena.graphical_controller.ExamEntityBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;

import java.util.List;

public class AverageUCC  {

    public ObservableList<ExamAverageInformation> getExamsArithmeticAverageInformation() throws ExamException{

        ObservableList<ExamEntityBean> exams = ExamsSubject.getInstance().getSortedExams();
        ObservableList<ExamAverageInformation> examsArithmeticAverageInfos = FXCollections.observableArrayList();
        Integer count = 1;
        double average = 0.0;
        double  counterVoti = 0.0 ;
        String data ;
        int voto ;

        for(ExamEntityBean exam : exams)
        {
            data = exam.getDate();
            voto = exam.getVotoEsame();
            counterVoti = counterVoti + voto;
            average = (counterVoti) / count;

            ExamAverageInformation info = new ExamAverageInformation();
            info.setAverage(average);
            info.setDate(data);

            examsArithmeticAverageInfos.add(info);
            count++;

        }

        return examsArithmeticAverageInfos;
    }

    public ObservableList<ExamAverageInformation> getExamsWeightedAverageInformation() throws ExamException {

        ObservableList<ExamEntityBean> exams = ExamsSubject.getInstance().getSortedExams() ;
        ObservableList<ExamAverageInformation> examsWeightedAverageInfos = FXCollections.observableArrayList();
        int  cfus = 0 ;
        double average = 0.0;
        double counterVoti = 0.0;
        int voto ;
        int cfu ;

        for(ExamEntityBean exam : exams)
        {
            String data = exam.getDate();
            voto = exam.getVotoEsame();
            cfu = exam.getCfuEsame();
            cfus = cfus + cfu ;
            counterVoti = counterVoti + (voto * cfu );

            average = (counterVoti) / cfus;

            ExamAverageInformation info = new ExamAverageInformation();
            info.setAverage(average);
            info.setDate(data);

            examsWeightedAverageInfos.add(info);
        }

        return examsWeightedAverageInfos;
    }
}
