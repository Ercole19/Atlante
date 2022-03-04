package com.example.athena.engineering_classes;

import com.example.athena.graphical_controller.ExamEntityBean;

import java.util.Comparator;

public class ExamsComparator implements Comparator<ExamEntityBean> {
    @Override
    public int compare(ExamEntityBean firstExam, ExamEntityBean secondExam) {
        return firstExam.getDate().compareTo(secondExam.getDate());
    }
}
