package com.example.athena.use_case_controllers;

import com.example.athena.entities.ExamDao;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class CareerStatusUCC {
    public ObservableList<PieChart.Data> retrieveExamPieChart() {
        ExamDao dao = new ExamDao();
        ObservableList<PieChart.Data> examsPieChartData = dao.loadData();
        return examsPieChartData;
    }

    public ObservableList<PieChart.Data> retrieveExamPieChartCfus() {
        ExamDao dao = new ExamDao();
        ObservableList<PieChart.Data> examsPieChartDataCfus = dao.loadData2();
        return examsPieChartDataCfus;
    }

    public int retrieveTotalExams() {
        ExamDao dao = new ExamDao() ;
        int totalExams = (int) dao.getTotalExams() ;
        return totalExams ;
    }
    public int retrieveTotalCfus() {
        ExamDao dao = new ExamDao() ;
        int totalCfus = (int) dao.getTotalCfus() ;
        return totalCfus ;
    }
}