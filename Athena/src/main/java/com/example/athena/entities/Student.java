package com.example.athena.entities;

import com.example.athena.dao.StudentDAO;
import com.example.athena.exceptions.PurchaseException;
import com.example.athena.exceptions.ReportException;
import com.example.athena.exceptions.StudentInfoException;

public class Student {
    private int repNum ;
    private String[] nameSurname;
    private String email;
    private int maxCfu;
    private int maxExams;
    private final StudentDAO studentDAO = new StudentDAO();

    public Student(String email) throws StudentInfoException {
        this.email = email;
        this.repNum = this.studentDAO.getTotalReport(email);
        this.nameSurname = this.studentDAO.getStudentFullName(email);
        this.maxCfu = this.studentDAO.getMaxCfu();
        this.maxExams = this.studentDAO.getMaxExams();
    }
    public Student() {}

    public String[] getFullName() throws StudentInfoException {
        return studentDAO.getStudentFullName(this.email);
    }

    public int getReportNumber() throws StudentInfoException {
        return studentDAO.getTotalReport(this.email);
    }

    public void setNewMax(int max, ExamsOrCfusEnum en) throws StudentInfoException {
        studentDAO.setCfusOrExams(max, en);
        if (en.toString().equals("SET_MAX_EXAMS")) this.maxExams = max;
        else this.maxCfu = max;
    }

    public void finalizePurchase(BookEntity book) throws PurchaseException {
        studentDAO.finalizePurchase(book.getTitle(), book.getIsbn(), Float.valueOf(book.getPrice()), book.getOwner(), book.getSaleTimestamp(), this.email);
    }
    public void reportSeller(String seller) throws ReportException {
        studentDAO.daoReportSeller(this.email, seller);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public int getMaxCfu() {
        return this.maxCfu;
    }

    public int getMaxExams() {
        return this.maxExams;
    }

    public String[] getNameSurname() {
        return this.nameSurname;
    }

    public int getRepNum() {
        return this.repNum;
    }
}
