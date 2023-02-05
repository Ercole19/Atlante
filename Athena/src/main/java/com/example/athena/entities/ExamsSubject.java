package com.example.athena.entities;

import com.example.athena.beans.OutputExamBean;
import com.example.athena.dao.ExamDao;
import com.example.athena.dao.UserDao;
import com.example.athena.engineering_classes.ExamsComparator;
import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import com.example.athena.exceptions.CareerStatusException;
import com.example.athena.exceptions.ExamException;
import com.example.athena.exceptions.UserInfoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class  ExamsSubject extends AbstractSubject {
   private List<EntityExam> totalExams = new ArrayList<>() ;
   private static ExamsSubject instance = null;

   private int takenExamsNumber ;
   private int gainedCfusNumber;
   
   private ExamsSubject()
   {
   }

   private void getCacheExams() throws ExamException
   {
       ExamDao eDao = new ExamDao();
       this.totalExams.addAll(eDao.getExamlist()) ;
       for (EntityExam exam: this.totalExams) {
           takenExamsNumber++;
           gainedCfusNumber = gainedCfusNumber + exam.getCfu();
       }
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
   
   public void deleteExam(EntityExam exam) throws ExamException
   {
       this.totalExams.remove(exam);
       this.takenExamsNumber--;
       this.gainedCfusNumber -= exam.getCfu();
       ExamDao examDao = new ExamDao();
       examDao.deleteExam(exam.getName());
       super.notifyObserver();
   }

   
   public ObservableList<OutputExamBean> getExams() throws ExamException {
       ObservableList<OutputExamBean> observableList = FXCollections.observableArrayList() ;
       if(totalExams.isEmpty()) {
           getCacheExams();
       }
       for (EntityExam exam : totalExams){
           OutputExamBean examEntityBean = new OutputExamBean();
           examEntityBean.setDate(exam.getDate());
           examEntityBean.setName(exam.getName());
           examEntityBean.setCfu(exam.getCfu());
           examEntityBean.setGrade(exam.getGrade());
           observableList.add(examEntityBean);
       }
       return observableList;
   }

   public ObservableList<OutputExamBean> getSortedExams()  throws ExamException {
       ExamsComparator comparator = new ExamsComparator();
       return this.getExams().sorted(comparator);
   }

   public int getTakenExamsNumber() throws ExamException
   {
       if (this.takenExamsNumber == -1) {
           getCacheExams();
       }
       return this.takenExamsNumber ;
   }

   public int getGainedCfusNumber () throws ExamException {
       if (this.gainedCfusNumber == -1) {
           getCacheExams();
       }
       return this.gainedCfusNumber;
   }

   public void logOut()
   {
       this.totalExams.clear() ;
       this.takenExamsNumber = -1 ;
       this.gainedCfusNumber = -1 ;
   }

}
