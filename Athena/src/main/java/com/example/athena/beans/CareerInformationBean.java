package com.example.athena.beans;

public class CareerInformationBean {

    private int totalExams;
    private int totalCfus;
    private int takenExams;
    private int gainedCfus;

    public int getGainedCfus() {
        return gainedCfus;
    }

    public int getTakenExams() {
        return takenExams;
    }

    public int getTotalCfus() {
        return totalCfus;
    }

    public int getTotalExams() {
        return totalExams;
    }

    public void setGainedCfus(int gainedCfus) {
        this.gainedCfus = gainedCfus;
    }

    public void setTakenExams(int takenExams) {
        this.takenExams = takenExams;
    }

    public void setTotalCfus(int totalCfus) {
        this.totalCfus = totalCfus;
    }

    public void setTotalExams(int totalExams) {
        this.totalExams = totalExams;
    }
}
