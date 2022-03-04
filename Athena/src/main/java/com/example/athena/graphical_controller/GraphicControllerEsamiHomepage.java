package com.example.athena.graphical_controller;


import com.example.athena.entities.ExamsSubject;
import com.example.athena.exceptions.ExamException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.engineering_classes.observer_pattern.AbstractObserver;
import com.example.athena.use_case_controllers.ManageExamsUCC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GraphicControllerEsamiHomepage implements Initializable {


    @FXML
    private TableView<ExamEntityBean> examTable ;
    @FXML
    private TableColumn<ExamEntityBean, String> colName ;
    @FXML
    private TableColumn<ExamEntityBean, Integer> colVote ;
    @FXML
    private TableColumn<ExamEntityBean, Integer> colCfu ;
    @FXML
    private TableColumn<ExamEntityBean, LocalDate> colDate ;
    @FXML
    private TableColumn<ExamEntityBean, Void> colEDit ;
    @FXML
    private Button averageBtn;
    @FXML
    private Button careerBtn;

    private Text cancella = null ;
    private Button editButton = null ;
    private HBox managebtn = null ;

    private final ObservableList<ExamEntityBean> examList  = FXCollections.observableArrayList() ;
    private final SceneSwitcher switcher = new SceneSwitcher();


    public void initAggiungiEsame ()  {
        switcher.popup("Aggiungi_Esame_View.fxml" , "Add exam") ;
        refreshTable();
        disableIfEmpty();
    }

    public void initMostraMedia () {
        switcher.popup("Mostra_Media_View.fxml" , "Average") ;
    }

    public void initCareerStatus ()  {

        switcher.popup("carrerStatusView.fxml" , "Career") ;
    }


    public void onBackButtonClick(ActionEvent event) throws IOException {
        ExamsSubject.getInstance().detachObserver(this);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        switcher.switcher(stage, "MainPageStudents.fxml");
     }


    public void refreshTable() {
        examList.clear() ;
        ExamPageUCC controller = new ExamPageUCC() ;
        examTable.setItems(controller.getList()) ;
    }

    public void disableIfEmpty() {
        ExamPageUCC controller = new ExamPageUCC();
        if (controller.getList().isEmpty())
        {
            averageBtn.setDisable(true);
            careerBtn.setDisable(true);
            averageBtn.setVisible(false);
            careerBtn.setVisible(false);
        }
        else
        {
            averageBtn.setVisible(true);
            averageBtn.setDisable(false);
            careerBtn.setDisable(false);
            careerBtn.setVisible(true);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<ExamEntityBean, String>("examName"));
        colVote.setCellValueFactory(new PropertyValueFactory<ExamEntityBean, Integer>("votoEsame"));
        colCfu.setCellValueFactory(new PropertyValueFactory<ExamEntityBean, Integer>("cfuEsame"));
        colDate.setCellValueFactory(new PropertyValueFactory<ExamEntityBean, LocalDate>("date"));
        javafx.util.Callback<TableColumn<ExamEntityBean, Void>, TableCell<ExamEntityBean, Void>> cellFactory = new javafx.util.Callback<TableColumn<ExamEntityBean, Void>, TableCell<ExamEntityBean, Void>>() {

            @Override
            public TableCell<ExamEntityBean, Void> call(TableColumn<ExamEntityBean, Void> examEntityBeanVoidTableColumn) {
                return new TableCell<ExamEntityBean, Void>() {


                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {

                            cancella = new Text("-");
                            cancella.setFont(Font.font("Arial Rounded MT Bold", 40));
                            cancella.setFill(Color.RED);

                            editButton = new Button("Edit ");

                            editButton.setOnAction(event -> {
                                ExamEntityBean exam = examTable.getSelectionModel().getSelectedItem();
                                SceneSwitcher switcher = new SceneSwitcher();
                                ArrayList<Object> params = new ArrayList<>();
                                params.add(exam);
                                switcher.popup("Aggiungi_Esame_view.fxml", "Edit your exam", params);
                                update();
                            });

                            cancella.setOnMouseClicked(event -> {
                                try {
                                    ManageExamsUCC controller = new ManageExamsUCC();
                                    controller.deleteExams(examTable.getSelectionModel().getSelectedItem());
                                } catch (Exception exc) {
                                    exc.getCause();
                                }
                            });

                            managebtn = new HBox(editButton, cancella);
                            managebtn.setStyle("-fx-alignment : center");
                            HBox.setMargin(editButton, new Insets(2, 2, 0, 3));
                            HBox.setMargin(cancella, new Insets(2, 3, 0, 2));
                            setGraphic(managebtn);

                        }
                    }


                };

            }
        };
        colEDit.setCellFactory(cellFactory);
        ExamsSubject.getInstance().attachObserver(this);

    }
}