package com.example.athena.graphical_controller;


import com.example.athena.entities.ExamDao;
import com.example.athena.use_case_controllers.ExamPageUCC;
import com.example.athena.use_case_controllers.SellBooksUseCaseController;
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
    private Text cancella = null ;
    private Button editButton = null ;
    private HBox managebtn = null ;

    private ObservableList<ExamEntityBean> examList  = FXCollections.observableArrayList() ;
    private ExamDao examDao ;


    public void initAggiungiEsame () throws IOException {

        SceneSwitcher switcher = new SceneSwitcher() ;
        switcher.popup("Aggiungi_Esame_View.fxml" , "Add exam") ;

    }
    public void initMostraMedia () throws IOException {
        SceneSwitcher switcher = new SceneSwitcher() ;
        switcher.popup("Mostra_Media_View.fxml" , "Average") ;
    }

    public void initCareerStatus () throws IOException {
        SceneSwitcher switcher = new SceneSwitcher() ;
        switcher.popup("carrerStatusView.fxml" , "Career") ;
    }


    public void onBackButtonClick(ActionEvent event) throws IOException {
        refreshTable();
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "MainPageStudents.fxml");
     }


    public void refreshTable() {
        examList.clear() ;

        ExamPageUCC controller = new ExamPageUCC() ;

        examTable.setItems(controller.getList()) ;
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
                        SceneSwitcher switcher = new SceneSwitcher() ;
                        ArrayList<Object> params = new ArrayList<>() ;
                        params.add(exam) ;
                        try {
                            switcher.popup("Aggiungi_Esame_view.fxml", "Edit your exam", params);
                            refreshTable();
                        }catch (IOException exc) {
                            exc.getCause() ;
                        }

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

        ExamPageUCC controller = new ExamPageUCC() ;


        examTable.setItems(controller.getList());







    }
}