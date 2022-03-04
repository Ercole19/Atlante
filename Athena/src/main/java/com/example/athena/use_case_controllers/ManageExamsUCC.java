package com.example.athena.use_case_controllers;

import com.example.athena.entities.EntityExam;
import com.example.athena.entities.ExamDao;
import com.example.athena.entities.ExamsSubject;
import com.example.athena.graphical_controller.ExamEntityBean;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ManageExamsUCC {
    
    public void deleteExams(ExamEntityBean bean) {
        EntityExam exam = new EntityExam(bean.getExamName(), bean.getVotoEsame(), bean.getCfuEsame(), bean.getDate());
        ExamsSubject.getInstance().deleteExam(exam, bean.getExamIndex());
    }

    public void addExam (ExamEntityBean bean) {

         EntityExam exam = new EntityExam(bean.getExamName(), bean.getVotoEsame(), bean.getCfuEsame(), bean.getDate()) ;
         ExamsSubject.getInstance().addExam(exam);

    }

    public void updateExamFromDB(ExamEntityBean newExam, ExamEntityBean oldExam) {

        EntityExam entityOldExam = new EntityExam(oldExam.getExamName(), oldExam.getVotoEsame(), oldExam.getCfuEsame() , oldExam.getDate());
        EntityExam entityNewExam = new EntityExam(newExam.getExamName(), newExam.getVotoEsame(), newExam.getCfuEsame(), newExam.getDate());
        ExamsSubject.getInstance().deleteExam(entityOldExam, oldExam.getExamIndex());
        ExamsSubject.getInstance().addExam(entityNewExam);
    }
}
















