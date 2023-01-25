package com.example.athena.beans;

import com.example.athena.exceptions.ExamException;

public class OracleExamBean implements ExamBean {

    private int examCfus ;
    private int examGrade ;
    private String examDate ;
    private String examName ;
    private int examIndex ;

    public void setExamCfus(String cfu) throws ExamException{
        if(!cfu.matches("\\d{2}")) throw new ExamException("Incorrect cfu format") ;
        this.examCfus = Integer.parseInt(cfu) ;
    }

    public void setExamGrade(String grade) throws ExamException {
        if(!grade.matches("\\d{2}")) throw new ExamException("Incorrect cfu format") ;
        this.examGrade = Integer.parseInt(grade) ;
    }

    public void setExamDate(String date) throws ExamException{
        if(!date.matches("\\d{4}-\\d{2}-\\d{2}")) throw new ExamException("Incorrect date format") ;
        this.examDate = date ;
    }

    public void setExamName(String examName) throws ExamException{
        if (!(examName.equals(""))) this.examName = examName;
        else throw new ExamException("Name can not be blank");

    }

    public void setExamIndex(int index) {
        this.examIndex = index ;
    }

    @Override
    public int getExamCfu() {
        return this.examCfus ;
    }

    @Override
    public int getExamGrade() {
        return this.examGrade ;
    }

    @Override
    public String getExamDate() {
        return this.examDate;
    }

    @Override
    public String getExamName() {
        return this.examName;
    }

    @Override
    public int getExamIndex() {
        return this.examIndex;
    }
}
