package com.example.athena.entities;

import com.example.athena.engineering_classes.ExamsComparator;
import com.example.athena.exceptions.ExamException;
import com.example.athena.graphical_controller.ExamEntityBean;
import com.example.athena.engineering_classes.observer_pattern.AbstractSubject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class ExamsSubject extends AbstractSubject {
   private List<EntityExam> totalExams ;
   private static ExamsSubject instance = null;
   
   private ExamsSubject()
   {
       ExamDao dao = new ExamDao();
       this.totalExams = dao.getExamlist();
   }
   
   public static synchronized ExamsSubject getInstance() 
   {
       if (instance == null) {
           instance = new ExamsSubject();
       }
       return instance;
   }
   
   public void addExam(EntityExam exam)
   {
       this.totalExams.add(exam);
       ExamDao examDao = new ExamDao();
       examDao.addExam(exam);
       super.notifyObserver() ;
   }
   
   public void deleteExam(EntityExam exam, int index)
   {
       this.totalExams.remove(index);
       ExamDao examDao = new ExamDao();
       examDao.deleteExam(exam.getNome());
       super.notifyObserver();
   }
   
   public ObservableList<ExamEntityBean> getExams() throws ExamException {
       ObservableList<ExamEntityBean> observableList = FXCollections.observableArrayList() ;
       int i = 0;
       for (EntityExam exam : totalExams){
           ExamEntityBean examEntityBean = new ExamEntityBean();
           examEntityBean.setDate(exam.getData());
           examEntityBean.setExamName(exam.getNome());
           examEntityBean.setCfuEsame(exam.getCfu());
           examEntityBean.setVotoEsame(exam.getVoto());
           examEntityBean.setExamIndex(i);
           i++;
           observableList.add(examEntityBean);
       }
       return observableList;
   }

   public ObservableList<ExamEntityBean> getSortedExams()  throws ExamException{
       ExamsComparator comparator = new ExamsComparator();
       ObservableList<ExamEntityBean> result = this.getExams().sorted(comparator);
       return result;
   }

}
