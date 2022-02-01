package com.example.athena.graphical_controller;


import com.example.athena.entities.ExamDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;



public class GraphicControllerEsamiHomepage implements Initializable {
    private Stage stage;
    private Scene scene;

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

    private ObservableList<ExamEntityBean> examList  = FXCollections.observableArrayList() ;
    private ExamDao examDao ;


    public void initAggiungiEsame () throws IOException {
        FXMLLoader loader = new FXMLLoader();
        SceneSwitcher switcher = new SceneSwitcher() ;
        loader.setLocation(switcher.generateUrl("Aggiungi_Esame_view.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        scene = new Scene(loader.load());
        stage.setTitle("Aggiungi esame");
        stage.setScene(scene);
        stage.showAndWait();

    }
    public void initMostraMedia () throws IOException {
        FXMLLoader loader = new FXMLLoader();
        SceneSwitcher switcher = new SceneSwitcher() ;
        loader.setLocation(switcher.generateUrl("Mostra_Media_View.fxml"));
        stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("La tua media");
        stage.showAndWait();
    }

    public void initCareerStatus () throws IOException {
        FXMLLoader loader = new FXMLLoader();
        SceneSwitcher switcher = new SceneSwitcher() ;
        loader.setLocation(switcher.generateUrl("carrerStatusView.fxml"));
        stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("La tua media");
        stage.showAndWait();
    }

    public void onBackButtonClick(ActionEvent event) throws IOException {
        refreshTable();
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageStudents.fxml");
     }


    public void refreshTable() {
        examList.clear() ;

        examDao = new ExamDao() ;
        examList = examDao.getExamlist() ;

        examTable.setItems(examList) ;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<ExamEntityBean, String>("examName"));
        colVote.setCellValueFactory(new PropertyValueFactory<ExamEntityBean, Integer>("votoEsame"));
        colCfu.setCellValueFactory(new PropertyValueFactory<ExamEntityBean, Integer>("cfuEsame"));
        colDate.setCellValueFactory(new PropertyValueFactory<ExamEntityBean, LocalDate>("date"));
        javafx.util.Callback<TableColumn<ExamEntityBean, Void>, TableCell<ExamEntityBean, Void>> cellFactory = new javafx.util.Callback<TableColumn<ExamEntityBean, Void> , TableCell<ExamEntityBean, Void>>() {


            @Override
            public TableCell<ExamEntityBean, Void> call(TableColumn<ExamEntityBean, Void> examEntityBeanVoidTableColumn) {
                return new TableCell<ExamEntityBean, Void>() {



            @Override
            public void updateItem (Void item , boolean empty ) {
                super.updateItem(item , empty);

                Text cancella = null ;
                Button editButton = null ;
                HBox managebtn = null ;
                if (empty ) {
                    setGraphic(null);
                }
                else {
                    cancella = new Text("-") ;
                    cancella.setFont(Font.font("Arial Rounded MT Bold" , 40)  );
                    cancella.setFill(Color.RED);

                    editButton = new Button("Edit ") ;

                    editButton.setOnAction(event -> {
                        ExamEntityBean exam = examTable.getSelectionModel().getSelectedItem();
                        FXMLLoader fxmlLoader =  new FXMLLoader();
                        SceneSwitcher switcher = new SceneSwitcher() ;
                        try {
                            fxmlLoader.setLocation(switcher.generateUrl("Aggiungi_Esame_view.fxml")) ;
                            fxmlLoader.load();

                        }catch (IOException exc) {
                            exc.getCause() ;
                        }
                        AddExamGraphicalController controller = fxmlLoader.getController() ;
                        controller.setNomeEsame(exam.getExamName());
                        controller.setVotoEsame(String.valueOf(exam.getVotoEsame()));
                        controller.setCfuEsame(String.valueOf(exam.getCfuEsame()));
                        controller.setDataEsame(exam.getDate());
                        controller.setOldExamName(exam.getExamName());
                        controller.setUpdate(true);






                    });


                    cancella.setOnMouseClicked( event -> {
                        try {
                            ExamEntityBean exam = examTable.getSelectionModel().getSelectedItem();
                            ExamDao esameD = new ExamDao() ;



                            esameD.deleteExam(exam.getExamName());
                            refreshTable();
                        }catch (Exception exc) {
                            exc.getCause() ;
                        }
                    });

                    managebtn = new HBox(editButton , cancella) ;
                    managebtn.setStyle("-fx-alignment : center");
                    HBox.setMargin(editButton, new Insets(2,2,0,3)) ;
                    HBox.setMargin(cancella , new Insets(2,3,0,2));
                    setGraphic(managebtn) ;

                }
            }


                } ;

            }
        };
        colEDit.setCellFactory(cellFactory);




        examDao = new ExamDao() ;
        examList = examDao.getExamlist() ;
        examTable.setItems(examList) ;


    }
}