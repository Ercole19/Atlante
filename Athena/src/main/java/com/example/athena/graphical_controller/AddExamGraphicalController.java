package com.example.athena.graphical_controller;


import com.example.athena.use_case_controllers.AddExamUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AddExamGraphicalController {
    private String oldExamName ;

    @FXML
    private TextField nomeEsame;
    @FXML
    private TextField votoEsame;
    @FXML
    private TextField cfuEsame;
    @FXML
    private DatePicker dataEsame;

    private boolean update ;



    public void confermaEsame(ActionEvent event) throws IOException {
          String nome = nomeEsame.getText();
          String data  = dataEsame.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ;
          String voto = votoEsame.getText();
          String cfu = cfuEsame.getText();

         ExamEntityBean examBean = new ExamEntityBean();

             examBean.setExamName(nome);
             examBean.setVotoEsame(voto);
             examBean.setCfuEsame(cfu);
             examBean.setDate(data);

         AddExamUseCaseController useCaseController = new AddExamUseCaseController() ;
         useCaseController.addExam(examBean , event , update , oldExamName);











    }
    public void indietro(ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setNomeEsame (String nome) {
        nomeEsame.setText(nome);
    }

    public void setVotoEsame (String voto) {
        votoEsame.setText(voto);
    }

    public void setCfuEsame (String cfu) {
        cfuEsame.setText(cfu) ;

    }
    public  void setDataEsame (String data) {
        dataEsame.setValue(LocalDate.parse(data));

    }
    public void setUpdate (boolean valore) {
        update = valore  ;
    }

    public void setOldExamName (String oldName) {
        oldExamName = oldName ;
    }


}
