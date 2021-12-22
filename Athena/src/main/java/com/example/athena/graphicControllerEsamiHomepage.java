package com.example.athena;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
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

    private ObservableList<examEntityBean> examList ;
    private examDAO examDao ;


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
        root = load(Objects.requireNonNull(getClass().getResource("MainPageStudents.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<examEntityBean, String>("examName"));
        colVote.setCellValueFactory(new PropertyValueFactory<examEntityBean , Integer>("votoEsame"));
        colCfu.setCellValueFactory(new PropertyValueFactory<examEntityBean , Integer>("cfuEsame"));
        colDate.setCellValueFactory(new PropertyValueFactory<examEntityBean , LocalDate>("date"));

        examList = FXCollections.observableArrayList() ;

        examDao = new examDAO() ;
        examList = examDao.getExamlist() ;
        examTable.setItems(examList) ;

    }
}