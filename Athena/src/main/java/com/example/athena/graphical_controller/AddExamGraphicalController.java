package com.example.athena.graphical_controller;


import com.example.athena.use_case_controllers.AddExamUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class AddExamGraphicalController implements PostInitialize {

    @FXML
    private TextField nomeEsame;
    @FXML
    private TextField votoEsame;
    @FXML
    private TextField cfuEsame;
    @FXML
    private DatePicker dataEsame;
    @FXML
    private Button confirm;
    private String oldName ;


    public void confermaEsame(ActionEvent event) {
        String nome = nomeEsame.getText();
        String data = dataEsame.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String voto = votoEsame.getText();
        String cfu = cfuEsame.getText();

        ExamEntityBean examBean = new ExamEntityBean();

        examBean.setExamName(nome);
        examBean.setVotoEsame(voto);
        examBean.setCfuEsame(cfu);
        examBean.setDate(data);

        AddExamUseCaseController useCaseController = new AddExamUseCaseController();
        useCaseController.addExam(examBean);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    public void updateExam(ActionEvent event) {

        ExamEntityBean bean = new ExamEntityBean() ;

        bean.setExamName(nomeEsame.getText());
        bean.setVotoEsame(votoEsame.getText());
        bean.setCfuEsame(cfuEsame.getText());
        bean.setDate(String.valueOf(dataEsame.getValue()));

        AddExamUseCaseController controller = new AddExamUseCaseController() ;
        controller.updateExamFromDB(bean, oldName);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();


    }


    public void indietro(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void postInitialize(ArrayList<Object> params) {


        ExamEntityBean exam = (ExamEntityBean) params.get(0);
        oldName = exam.getExamName() ;

        nomeEsame.setText(exam.getExamName());
        votoEsame.setText(String.valueOf(exam.getVotoEsame()));
        cfuEsame.setText(String.valueOf(exam.getCfuEsame()));
        dataEsame.setValue(LocalDate.parse(exam.getDate()));


        confirm.setText("Update");
        confirm.setOnAction(this::updateExam) ;
    }


}


