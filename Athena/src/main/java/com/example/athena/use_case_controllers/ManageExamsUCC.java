package com.example.athena.use_case_controllers;

import com.example.athena.beans.ExamBean;
import com.example.athena.entities.EntityExam;
import com.example.athena.entities.ExamsSubject;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.CareerStatusException;
import com.example.athena.exceptions.ExamException;

public class ManageExamsUCC {
    
    public void deleteExams(ExamBean bean) throws ExamException {
        EntityExam exam = new EntityExam(bean.getExamName(), bean.getExamGrade(), bean.getExamCfu(), bean.getExamDate());
        ExamsSubject.getInstance().deleteExam(exam);
    }

    public void addExam (ExamBean bean) throws ExamException{
         if (bean.getExamCfu() + ExamsSubject.getInstance().getGainedCfusNumber() > LoggedStudent.getInstance().getCurrentStudent().getMaxCfu() || ExamsSubject.getInstance().getTakenExamsNumber() +1 > LoggedStudent.getInstance().getCurrentStudent().getMaxExams() ) {
             throw new ExamException("Error, you are exceeding total cfu number or total exams number !");
         }
         else {
             EntityExam exam = new EntityExam(bean.getExamName(), bean.getExamGrade(), bean.getExamCfu(), bean.getExamDate()) ;
             ExamsSubject.getInstance().addExam(exam);
         }

    }

    public void updateExamFromDB(ExamBean newExam, ExamBean oldExam) throws ExamException {

        if (newExam.getExamCfu() + ExamsSubject.getInstance().getGainedCfusNumber() > LoggedStudent.getInstance().getCurrentStudent().getMaxCfu()) {

            throw new ExamException("Error, you are exceeding total cfu number !");
        }
        else {
            EntityExam entityOldExam = new EntityExam(oldExam.getExamName(), oldExam.getExamGrade(), oldExam.getExamCfu(), oldExam.getExamDate());
            EntityExam entityNewExam = new EntityExam(newExam.getExamName(), newExam.getExamGrade(), newExam.getExamCfu(), newExam.getExamDate());
            ExamsSubject.getInstance().deleteExam(entityOldExam);
            ExamsSubject.getInstance().addExam(entityNewExam);
        }
    }
}
















