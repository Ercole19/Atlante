package com.example.athena.use_case_controllers;

import com.example.athena.entities.EntityExam;
import com.example.athena.entities.ExamsSubject;
import com.example.athena.exceptions.ExamException;
import com.example.athena.graphical_controller.ExamEntityBean;

public class ManageExamsUCC {
    
    public void deleteExams(ExamEntityBean bean) throws ExamException {
        EntityExam exam = new EntityExam(bean.getExamName(), bean.getExamGrade(), bean.getExamCfu(), bean.getExamDate());
        ExamsSubject.getInstance().deleteExam(exam, bean.getExamIndex());
    }

    public void addExam (ExamEntityBean bean) throws ExamException{

         EntityExam exam = new EntityExam(bean.getExamName(), bean.getExamGrade(), bean.getExamCfu(), bean.getExamDate()) ;
         ExamsSubject.getInstance().addExam(exam);

    }

    public void updateExamFromDB(ExamEntityBean newExam, ExamEntityBean oldExam) throws ExamException {

        EntityExam entityOldExam = new EntityExam(oldExam.getExamName(), oldExam.getExamGrade(), oldExam.getExamCfu() , oldExam.getExamDate());
        EntityExam entityNewExam = new EntityExam(newExam.getExamName(), newExam.getExamGrade(), newExam.getExamCfu(), newExam.getExamDate());
        ExamsSubject.getInstance().deleteExam(entityOldExam, oldExam.getExamIndex());
        ExamsSubject.getInstance().addExam(entityNewExam);
    }
}















