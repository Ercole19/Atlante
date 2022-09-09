package com.example.athena.beans;

import java.util.Date;

public class OutputExamBean implements ExamBean{
    private String date;
    private int grade;
    private int cfu;
    private String name;

    public void setDate (String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }


    @Override
    public int getExamCfu() {
        return this.cfu;
    }

    @Override
    public int getExamGrade() {
        return this.grade;
    }

    @Override
    public String getExamDate() {
        return this.date;
    }

    @Override
    public String getExamName() {
        return this.name;
    }

    @Override
    public int getExamIndex() {
        return 0;
    }
}
