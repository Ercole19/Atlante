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

    public void setCfuEsame(String  cfuString)  {
            try {
                cfuEsame = Integer.parseInt(cfuString) ;
            } catch (NumberFormatException e )  {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Exam fields are not valid, check if you fill them correctly  ", ButtonType.CLOSE);
                alert.showAndWait();
                return;

            }
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setVotoEsame(String votoString) {
        try {
            votoEsame = Integer.parseInt(votoString) ;
        }catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, " Exam fields are not valid, check if you fill them correctly  ", ButtonType.CLOSE);
            alert.showAndWait();
        }
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
