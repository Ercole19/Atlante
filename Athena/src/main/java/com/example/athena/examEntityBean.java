package com.example.athena;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.LocalDate;

public class examEntityBean {
    private String examName;
    private int votoEsame;
    private int cfuEsame;
    private LocalDate date;

    public examEntityBean (String nomeString , String votoString , String cfuString , LocalDate esameData) {
        try {
            this.votoEsame = Integer.parseInt(votoString);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, " Fill correctly all the fields ", ButtonType.CLOSE);
            alert.showAndWait();
            return;

        }
        try {
            this.cfuEsame = Integer.parseInt(cfuString);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, " Fill correctly all the fields ", ButtonType.CLOSE);
            alert.showAndWait();
            return;

        }
        this.examName = nomeString ;
        this.date = esameData ;
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
