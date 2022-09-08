package com.example.athena.beans.normal;



import com.example.athena.exceptions.ExamException;

public class ExamEntityBean  {
    private String examName;
    private int examGrade;
    private int examCfu;
    private String examDate;
    private int examIndex;


    public void setExamCfu(int cfus)  {
         this.examCfu = cfus;
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

    public void setExamDate(String data ) {
        this.examDate = data ;
    }

    public void setExamGrade(int examGrade) {
       this.examGrade = examGrade;
    }

    public int getExamCfu() {
        return examCfu;
    }

    public int getExamGrade() {
        return examGrade;
    }

    public String getExamDate() {
        return examDate;
    }

    public String getExamName() {
        return examName;
    }

    public int getExamIndex() {
        return examIndex;
    }

    public void setExamIndex(int examIndex) {
        this.examIndex = examIndex;
    }
}
