package com.example.athena.graphical_controller;



import com.example.athena.exceptions.ExamException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ExamEntityBean  {
    private String examName;
    private int votoEsame;
    private int cfuEsame;
    private String  date;
    private int examIndex;


    public void setCfuEsame(int cfus)  {
         this.cfuEsame = cfus;
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

    public void setDate(String data ) {
        this.date = data ;
    }

    public void setVotoEsame(int examGrade) {
       this.votoEsame = examGrade;
    }

    public int getCfuEsame() {
        return cfuEsame;
    }

    public int getVotoEsame() {
        return votoEsame;
    }

    public String getDate() {
        return date;
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
