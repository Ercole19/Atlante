package com.example.athena.use_case_controllers;

import com.example.athena.entities.ExamDao;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class AverageUCC {

    public ObservableList<XYChart.Data<String, Number>> retrieveExams() {
        ExamDao dao = new ExamDao() ;
        ObservableList<XYChart.Data<String, Number>> data = dao.getSortedExams() ;
        return data ;
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
