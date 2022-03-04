package com.example.athena.use_case_controllers;

import com.example.athena.entities.EntityExam;
import com.example.athena.entities.ExamDao;
import com.example.athena.entities.ExamsSubject;
import com.example.athena.graphical_controller.ExamEntityBean;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AddExamUseCaseController {



    public void addExam (ExamEntityBean bean) {

         EntityExam exam = new EntityExam(bean.getExamName(), bean.getVotoEsame(), bean.getCfuEsame(), bean.getDate()) ;
         ExamsSubject.getInstance().addExam(exam);

    }

    public void updateExamFromDB(ExamEntityBean exam, String oldname) {

        ExamDao dao = new ExamDao() ;
        dao.updateExam(exam, oldname);

    }
}
















