package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.entities.ExamsSubject;
import com.example.athena.exceptions.ExamException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.engineering_classes.observer_pattern.AbstractObserver;
import com.example.athena.beans.ExamEntityBean;
import com.example.athena.exceptions.UserInfoException;
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


public class ExamsHomePageGCC implements AbstractObserver, Initializable {


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

    private Text delete = null ;
    private Button editButton = null ;
    private HBox manageBtn = null ;

    private final ObservableList<ExamEntityBean> examList  = FXCollections.observableArrayList() ;
    private final SceneSwitcher switcher = new SceneSwitcher();


    public void onAddExamBtnClick()  {
        switcher.popup("AddExamView.fxml" , "Add exam") ;
    }

    public void onAverageBtnClick() {
        switcher.popup("AveragePageView.fxml" , "Average") ;
    }

    public void onCareerBtnClick()  {
        switcher.popup("careerStatusView.fxml" , "Career") ;
    }

    public void onBackButtonClick(ActionEvent event) throws IOException {
        ExamsSubject.getInstance().detachObserver(this);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        switcher.switcher(stage, "MainPageStudents.fxml");
     }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<>("examName"));
        colVote.setCellValueFactory(new PropertyValueFactory<>("examGrade"));
        colCfu.setCellValueFactory(new PropertyValueFactory<>("examCfu"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("examDate"));
        javafx.util.Callback<TableColumn<ExamEntityBean, Void>, TableCell<ExamEntityBean, Void>> cellFactory = new javafx.util.Callback<>() {

            @Override
            public TableCell<ExamEntityBean, Void> call(TableColumn<ExamEntityBean, Void> examEntityBeanVoidTableColumn) {
                return new TableCell<>() {

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {

                            delete = new Text("-");
                            delete.setFont(Font.font("Arial Rounded MT Bold", 40));
                            delete.setFill(Color.RED);

                            editButton = new Button("Edit ");

                            editButton.setOnAction(event -> {
                                ExamEntityBean exam = examTable.getSelectionModel().getSelectedItem();
                                ArrayList<Object> params = new ArrayList<>();
                                params.add(exam);
                                switcher.popup("AddExamView.fxml", "Edit your exam", params);
                                update();
                            });

                            delete.setOnMouseClicked(event -> {
                                try {
                                    ManageExamsUCC controller = new ManageExamsUCC();
                                    controller.deleteExams(examTable.getSelectionModel().getSelectedItem());
                                }
                                catch (ExamException exc){
                                    SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, exc.getMessage());
                                    alert.showAndWait();
                                    return;
                                }
                            });

                            manageBtn = new HBox(editButton, delete);
                            manageBtn.setStyle("-fx-alignment : center");
                            HBox.setMargin(editButton, new Insets(2, 2, 0, 3));
                            HBox.setMargin(delete, new Insets(2, 3, 0, 2));
                            setGraphic(manageBtn);
                        }
                    }
                };
            }
        };
        colEDit.setCellFactory(cellFactory);
        ExamsSubject.getInstance().attachObserver(this);
    }

    public void update()
    {
        examList.clear() ;
        ObservableList<ExamEntityBean> totalExams = FXCollections.observableArrayList() ;
        try {
            totalExams = ExamsSubject.getInstance().getExams();
        } catch (ExamException | UserInfoException e) {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
        examTable.setItems(totalExams);

        if (totalExams.isEmpty())
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
}