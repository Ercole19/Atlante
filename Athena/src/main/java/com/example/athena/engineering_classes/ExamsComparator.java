package com.example.athena.engineering_classes;

import com.example.athena.beans.OutputExamBean;

import java.util.Comparator;

public class ExamsComparator implements Comparator<OutputExamBean> {

    @Override
    public int compare(OutputExamBean firstExam, OutputExamBean secondExam) {
        return firstExam.getExamDate().compareTo(secondExam.getExamDate());
    }
}
