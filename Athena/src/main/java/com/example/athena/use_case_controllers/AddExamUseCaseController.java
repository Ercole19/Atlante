package com.example.athena.use_case_controllers;

import com.example.athena.entities.EntityExam;
import com.example.athena.graphical_controller.ExamEntityBean;
import com.example.athena.entities.ExamDao;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class AddExamUseCaseController {





    public void addExam (ExamEntityBean bean) {


        if ((bean.getExamName().equals("")) || (bean.getVotoEsame() < 18 || bean.getVotoEsame() > 30) || (bean.getCfuEsame() < 0 || bean.getCfuEsame() > 15) ) {
            Alert alert = new Alert(Alert.AlertType.ERROR, " Exam fields are not valid, check if you fill them correctly  ", ButtonType.CLOSE);
            alert.showAndWait();

        } else {
             ExamDao esame = new ExamDao();
             EntityExam exam = new EntityExam(bean.getExamName(), bean.getVotoEsame(), bean.getCfuEsame(), bean.getDate()) ;
             esame.addExam(exam);
        }
    }

    public void updateExamFromDB(ExamEntityBean exam, String oldname) {

        ExamDao dao = new ExamDao() ;
        dao.updateExam(exam, oldname);

    }
}
















