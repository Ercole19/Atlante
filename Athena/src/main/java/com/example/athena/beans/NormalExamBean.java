package com.example.athena.beans;


import com.example.athena.exceptions.ExamException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NormalExamBean implements ExamBean {
    private String examName;
    private int examGrade;
    private int examCfu;
    private String examDate;


    public void setExamCfu(String cfus) throws ExamException {
        try {
            this.examCfu = Integer.parseInt(cfus);
        }
        catch (NumberFormatException e) {
            throw new ExamException("Incorrect argument passed");
        }
    }

    public void setExamName(String examName) throws ExamException {
        if(!(examName.equals("")))
        {
            this.examName = examName;
        }
        else
        {
            throw new ExamException("Exam's name must be not blank");
        }
    }

    public void setExamDate(LocalDate date) throws DateTimeException {
        
        this.examDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))  ;
    }

    public void setExamGrade(String  examGrade) throws ExamException {
        try {
            this.examGrade = Integer.parseInt(examGrade);
        }
       catch (NumberFormatException e) {
            throw new ExamException("Incorrect argument passed");
       }
    }

    @Override
    public int getExamCfu() {
        return examCfu;
    }

    @Override
    public int getExamGrade() {
        return examGrade;
    }

    @Override
    public String getExamDate() {
        return examDate;
    }

    @Override
    public String getExamName() {
        return examName;
    }
}
