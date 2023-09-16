package com.example.athena.beans;

public class StudentInfosBean {
    private String student;
    private String[] fullName;
    private int repNum;

    public int getRepNum() {
        return repNum;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String[] getFullName() {
        return fullName;
    }

    public void setFullName(String[] fullName) {
        this.fullName = fullName;
    }

    public void setRepNum(int repNum) {
        this.repNum = repNum;
    }
}
