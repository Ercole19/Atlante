package com.example.athena.GraphicalController;


import com.example.athena.Entities.examdao;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;



public class graphicControllerEsamiHomepage implements Initializable {
    private Stage stage;
    private Scene scene;

    @FXML
    private TableView<examEntityBean> examTable ;
    @FXML
    private TableColumn<examEntityBean , String> colName ;
    @FXML
    private TableColumn<examEntityBean , Integer> colVote ;
    @FXML
    private TableColumn<examEntityBean , Integer> colCfu ;
    @FXML
    private TableColumn<examEntityBean, LocalDate> colDate ;
    @FXML
    private TableColumn<examEntityBean , Void> colEDit ;

    private ObservableList<examEntityBean> examList  = FXCollections.observableArrayList() ;
    private examdao examDao ;


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

        examDao = new examdao() ;
        examList = examDao.getExamlist() ;

        examTable.setItems(examList) ;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<examEntityBean, String>("examName"));
        colVote.setCellValueFactory(new PropertyValueFactory<examEntityBean , Integer>("votoEsame"));
        colCfu.setCellValueFactory(new PropertyValueFactory<examEntityBean , Integer>("cfuEsame"));
        colDate.setCellValueFactory(new PropertyValueFactory<examEntityBean , LocalDate>("date"));
        javafx.util.Callback<TableColumn<examEntityBean, Void>, TableCell<examEntityBean, Void>> cellFactory = new javafx.util.Callback<TableColumn<examEntityBean , Void> , TableCell<examEntityBean , Void>>() {


            @Override
            public TableCell<examEntityBean, Void> call(TableColumn<examEntityBean, Void> examEntityBeanVoidTableColumn) {
                return new TableCell<examEntityBean , Void>() {



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
                        examEntityBean exam = examTable.getSelectionModel().getSelectedItem();
                        FXMLLoader fxmlLoader =  new FXMLLoader();
                        SceneSwitcher switcher = new SceneSwitcher() ;
                        try {
                            fxmlLoader.setLocation(switcher.generateUrl("Aggiungi_Esame_view.fxml")) ;
                            fxmlLoader.load();

                        }catch (IOException exc) {
                            exc.getCause() ;
                        }
                        addExamGraphicalController controller = fxmlLoader.getController() ;
                        controller.setNomeEsame(exam.getExamName());
                        controller.setVotoEsame(String.valueOf(exam.getVotoEsame()));
                        controller.setCfuEsame(String.valueOf(exam.getCfuEsame()));
                        controller.setDataEsame(exam.getDate());
                        controller.setOldExamName(exam.getExamName());
                        controller.setUpdate(true);
                        Parent parent = fxmlLoader.getRoot() ;
                        stage = new Stage() ;
                        stage.setScene(new Scene(parent) );
                        stage.initStyle(StageStyle.UTILITY);
                        stage.show();





                    });


                    cancella.setOnMouseClicked( event -> {
                        try {
                            examEntityBean exam = examTable.getSelectionModel().getSelectedItem();
                            examdao esameD = new examdao() ;



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




        examDao = new examdao() ;
        examList = examDao.getExamlist() ;
        examTable.setItems(examList) ;


    }
}