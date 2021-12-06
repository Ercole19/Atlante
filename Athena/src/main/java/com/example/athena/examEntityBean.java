package com.example.athena;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;

public class examEntityBean {
    private String examName;
    private int votoEsame;
    private int cfuEsame;
    private LocalDate date;

    public examEntityBean () {

    }

    public void setCfuEsame(String  cfuString) throws NumberFormatException {
            this.cfuEsame = Integer.parseInt(cfuString);
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setVotoEsame(String votoString)  throws  NumberFormatException{
            this.votoEsame = Integer.parseInt(votoString);
    }

    public int getCfuEsame() {
        return cfuEsame;
    }

    public int getVotoEsame() {
        return votoEsame;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getExamName() {
        return examName;
    }
}
