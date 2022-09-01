package com.example.athena.entities;


public class EntityExam {
    protected String name;
    protected int grade;
    protected int cfu;
    protected String date;
    

    public EntityExam(String name, int grade, int cfu, String  date) {
        this.name = name;
        this.grade = grade;
        this.cfu = cfu;
        this.date = date;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public int getCfu() {
        return cfu;
    }

    public String getDate() {
        return date;
    }
    
}