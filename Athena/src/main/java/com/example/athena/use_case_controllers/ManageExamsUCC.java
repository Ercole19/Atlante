package com.example.athena.use_case_controllers;

import com.example.athena.beans.ExamBean;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.entities.PersonalTakenExams;
import com.example.athena.exceptions.ExamException;

public class ManageExamsUCC {
    
    public void deleteExams(ExamBean bean) throws ExamException {
        PersonalTakenExams.getInstance().deleteExam(bean);
    }

    public void addExam (ExamBean bean) throws ExamException{
         if (bean.getExamCfu() + PersonalTakenExams.getInstance().getGainedCfusNumber() > LoggedStudent.getInstance().getCurrentStudent().getMaxCfu() || PersonalTakenExams.getInstance().getTakenExamsNumber() +1 > LoggedStudent.getInstance().getCurrentStudent().getMaxExams() ) {
             throw new ExamException("Error, you are exceeding total cfu number or total exams number !");
         }
         else {
             PersonalTakenExams.getInstance().addExam(bean);
         }
    }

    public void updateExamFromDB(ExamBean newExam, ExamBean oldExam) throws ExamException {

        if (newExam.getExamCfu() + PersonalTakenExams.getInstance().getGainedCfusNumber() > LoggedStudent.getInstance().getCurrentStudent().getMaxCfu()) {

            throw new ExamException("Error, you are exceeding total cfu number !");
        }
        else {
            PersonalTakenExams.getInstance().deleteExam(oldExam);
            PersonalTakenExams.getInstance().addExam(newExam);
        }
    }
}
















