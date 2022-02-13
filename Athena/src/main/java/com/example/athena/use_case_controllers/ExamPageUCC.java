package com.example.athena.use_case_controllers;

import com.example.athena.entities.BookDao;
import com.example.athena.entities.BookEntity;
import com.example.athena.entities.EntityExam;
import com.example.athena.entities.ExamDao;
import com.example.athena.graphical_controller.BookEntityBean;
import com.example.athena.graphical_controller.ExamEntityBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ExamPageUCC {

    public ObservableList<ExamEntityBean> getList() {
        ObservableList<ExamEntityBean> examBeanList = FXCollections.observableArrayList();
        List<EntityExam> examList  ;
        ExamDao examDao = new ExamDao();
        examList = examDao.getExamlist();
        for (EntityExam entity : examList) {

            ExamEntityBean bean = new ExamEntityBean() ;
            bean.setExamName(entity.getNome());
            bean.setVotoEsame(String.valueOf(entity.getVoto()));
            bean.setCfuEsame(String.valueOf(entity.getCfu()));
            bean.setDate(entity.getData());

            examBeanList.add(bean);

        }

        return  examBeanList;

    }
    }




