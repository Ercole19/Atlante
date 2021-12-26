package com.example.athena;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;



public class graphicControllerEsamiHomepage implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
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
        root = FXMLLoader.load(getClass().getResource("Aggiungi_Esame_view.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        scene = new Scene(root);
        stage.setTitle("Aggiungi esame");
        stage.setScene(scene);
        stage.showAndWait();

    }
    public void initMostraMedia () throws IOException {
        root = FXMLLoader.load(getClass().getResource("Mostra_Media_View.fxml"));
        stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("La tua media");
        stage.showAndWait();
    }

    public void initCareerStatus () throws IOException {
        root = FXMLLoader.load(getClass().getResource("carrerStatusView.fxml"));
        stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("La tua media");
        stage.showAndWait();
    }

    public void onBackButtonClick(ActionEvent event) throws IOException {
        refreshTable();
        root = load(Objects.requireNonNull(getClass().getResource("MainPageStudents.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

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
                final TableCell<examEntityBean , Void> cell = new TableCell<examEntityBean , Void>() {



            @Override
            public void updateItem (Void item , boolean empty ) {
                super.updateItem(item , empty);
                if (empty ) {
                    setGraphic(null);
                }
                else {
                    final Text cancella = new Text("-") ;
                    cancella.setFont(Font.font("Arial Rounded MT Bold" , 40)  );
                    cancella.setFill(Color.RED);

                    final Button editButton = new Button("Edit ") ;

                    editButton.setOnAction(event -> {
                        examEntityBean exam = examTable.getSelectionModel().getSelectedItem();
                        FXMLLoader fxmlLoader =  new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("Aggiungi_Esame_view.fxml" ));
                        try {
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
                        Stage stage = new Stage() ;
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

                    HBox managebtn = new HBox(editButton , cancella) ;
                    managebtn.setStyle("-fx-alignment : center");
                    HBox.setMargin(editButton, new Insets(2,2,0,3)) ;
                    HBox.setMargin(cancella , new Insets(2,3,0,2));
                    setGraphic(managebtn) ;

                }
            }


                } ;
                return cell ;
            }
        };
        colEDit.setCellFactory(cellFactory);




        examDao = new examdao() ;
        examList = examDao.getExamlist() ;
        examTable.setItems(examList) ;


    }
}