package com.example.athena.entities;

import com.example.athena.engineering_classes.ExamsComparator;
import com.example.athena.exceptions.CareerStatusException;
import com.example.athena.exceptions.ExamException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.graphical_controller.ExamEntityBean;
import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;

public class  ExamsSubject extends AbstractSubject {
   private List<EntityExam> totalExams = new ArrayList<>() ;
   private static ExamsSubject instance = null;

   private int examsNumber ;
   private int cfusNumber ;
   
   private ExamsSubject()
   {
   }

   private void getCacheExams()
   {
       try {
           ExamDao eDao = new ExamDao();
           this.totalExams.addAll(eDao.getExamlist()) ;

           UserDao uDao = new UserDao();
           this.examsNumber = uDao.getAllExams();
           this.cfusNumber = uDao.getAllCfus();
       }
       catch (ExamException exc) {
           SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, exc.getMessage());
           alert.showAndWait();
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
       ExamDao examDao = new ExamDao();
       examDao.addExam(exam);
       super.notifyObserver() ;
   }
   
   public void deleteExam(EntityExam exam, int index) throws ExamException
   {
       this.totalExams.remove(index);
       ExamDao examDao = new ExamDao();
       examDao.deleteExam(exam.getName());
       super.notifyObserver();
   }

   public void setNewMax(int max, ExamsOrCfusEnum type) throws CareerStatusException {
       UserDao dao = new UserDao();
       dao.setCfusOrExams(max, type);

       if (type.toString().equals(("SET_MAX_CFUS"))) {this.cfusNumber = max;}
       else {this.examsNumber = max;}

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

   public int getExamsNumber()
   {
       if(this.examsNumber == -1) {
           getCacheExams();
       }
       return this.examsNumber ;
   }

   public int getCfusNumber()
   {
       if(this.examsNumber == -1) {
           getCacheExams();
       }
       return this.cfusNumber ;
   }

   public void logOut()
   {
       this.totalExams.clear() ;
       this.examsNumber = -1 ;
       this.cfusNumber = -1 ;
   }

}
