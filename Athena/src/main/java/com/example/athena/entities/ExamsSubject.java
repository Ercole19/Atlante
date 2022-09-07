package com.example.athena.entities;

import com.example.athena.engineering_classes.ExamsComparator;
import com.example.athena.exceptions.CareerStatusException;
import com.example.athena.exceptions.ExamException;
import com.example.athena.beans.ExamEntityBean;
import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import com.example.athena.exceptions.UserInfoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class  ExamsSubject extends AbstractSubject {
   private List<EntityExam> totalExams = new ArrayList<>() ;
   private static ExamsSubject instance = null;

   private int totalExamsNumber;
   private int totalCfus;
   private int takenExamsNumber ;
   private int gainedCfusNumber;
   
   private ExamsSubject()
   {
   }

   private void getCacheExams() throws ExamException, UserInfoException
   {
       ExamDao eDao = new ExamDao();
       this.totalExams.addAll(eDao.getExamlist()) ;

       UserDao uDao = new UserDao();
       this.totalExamsNumber = uDao.getAllExams();
       this.totalCfus = uDao.getAllCfus();
       this.takenExamsNumber = eDao.getTakenExamsNumber(User.instance.getEmail());
       this.gainedCfusNumber = eDao.getTakenCfus(User.instance.getEmail());
   }
   
   public static synchronized ExamsSubject getInstance() 
   {
       if (instance == null) {
           instance = new ExamsSubject();
       }
       return instance;
   }
   
   public void addExam(EntityExam exam) throws ExamException
   {
       this.totalExams.add(exam);
       this.takenExamsNumber++ ;
       this.gainedCfusNumber += exam.getCfu() ;
       ExamDao examDao = new ExamDao();
       examDao.addExam(exam);
       super.notifyObserver() ;
   }
   
   public void deleteExam(EntityExam exam, int index) throws ExamException
   {
       this.totalExams.remove(index);
       this.takenExamsNumber--;
       this.gainedCfusNumber -= exam.getCfu();
       ExamDao examDao = new ExamDao();
       examDao.deleteExam(exam.getName());
       super.notifyObserver();
   }

   public void setNewMax(int max, ExamsOrCfusEnum type) throws CareerStatusException {
       UserDao dao = new UserDao();
       dao.setCfusOrExams(max, type);

       if (type.toString().equals(("SET_MAX_CFUS"))) {this.totalCfus = max;}
       else {this.totalExamsNumber = max;}

       super.notifyObserver();
   }
   
   public ObservableList<ExamEntityBean> getExams() throws ExamException {
       ObservableList<ExamEntityBean> observableList = FXCollections.observableArrayList() ;
       int i = 0;
       if(totalExams.isEmpty()) {
           getCacheExams();
       }
       for (EntityExam exam : totalExams){
           ExamEntityBean examEntityBean = new ExamEntityBean();
           examEntityBean.setExamDate(exam.getDate());
           examEntityBean.setExamName(exam.getName());
           examEntityBean.setExamCfu(exam.getCfu());
           examEntityBean.setExamGrade(exam.getGrade());
           examEntityBean.setExamIndex(i);
           i++;
           observableList.add(examEntityBean);
       }
       return observableList;
   }

   public ObservableList<ExamEntityBean> getSortedExams()  throws ExamException{
       ExamsComparator comparator = new ExamsComparator();
       return this.getExams().sorted(comparator);
   }

   public int getTotalExamsNumber() throws ExamException, UserInfoException
   {
       if(this.totalExamsNumber == -1) {
           getCacheExams();
       }
       return this.totalExamsNumber;
   }

   public int getTotalCfusNumber() throws ExamException, UserInfoException
   {
       if(this.totalCfus == -1) {
           getCacheExams();
       }
       return this.totalCfus;
   }

   public int getTakenExamsNumber() throws ExamException, UserInfoException
   {
       if (this.takenExamsNumber == -1) {
           getCacheExams();
       }
       return this.takenExamsNumber ;
   }

   public int getGainedCfusNumber () throws ExamException, UserInfoException {
       if (this.gainedCfusNumber == -1) {
           getCacheExams();
       }
       return this.gainedCfusNumber;
   }

   public void logOut()
   {
       this.totalExams.clear() ;
       this.totalExamsNumber = -1 ;
       this.totalCfus = -1 ;
       this.takenExamsNumber = -1 ;
       this.gainedCfusNumber = -1 ;
   }

}
