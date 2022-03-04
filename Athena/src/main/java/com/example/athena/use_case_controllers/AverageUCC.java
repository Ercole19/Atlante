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

    public ObservableList<XYChart.Data<String, Number>> retrieveExamsWeighted() {
        ExamDao dao = new ExamDao() ;
        ObservableList<XYChart.Data<String, Number>> data = dao.getSortedExamsWeighted() ;
        return data ;
    }
    public Number retrieveAverage() {
        ExamDao dao = new ExamDao();
        Number average = dao.getAverage();
        return average;
    }

    public Number retrieveWeightedAverage() {
        ExamDao dao = new ExamDao() ;
        Number weightedAverage = dao.getAverageWeighted();
        return weightedAverage ;
    }
}
