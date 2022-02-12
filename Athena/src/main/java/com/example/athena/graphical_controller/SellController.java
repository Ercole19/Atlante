package com.example.athena.graphical_controller;


import com.example.athena.entities.ExamDao;
import com.example.athena.exceptions.BookException;
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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SellController implements Initializable {

    private final SceneSwitcher switcher = new SceneSwitcher();

    @FXML
    private TableView<BookEntityBean> bookTable ;

    @FXML
    private TableColumn<BookEntityBean, String> colName ;

    @FXML
    private TableColumn<BookEntityBean, String> colIsbn ;

    @FXML
    private TableColumn<BookEntityBean, Float> colPrice ;

    @FXML
    private TableColumn<BookEntityBean, Void> colManage ;


    private ObservableList<BookEntityBean> bookList  = FXCollections.observableArrayList() ;

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        switcher.switcher(event, "bookshop-choose-view.fxml");
    }

    public void onSellBtnClick() throws IOException{
        switcher.popup("sellBookModule.fxml", "Sell a book") ;
    }


    public void refreshTable() throws BookException {
        bookList.clear() ;

        SellBooksUseCaseController controller = new SellBooksUseCaseController() ;

        bookTable.setItems(controller.getBookList()) ;
    }








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<>("title"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colManage.setCellValueFactory(new PropertyValueFactory<>("Manage"));
        javafx.util.Callback<TableColumn<BookEntityBean, Void>, TableCell<BookEntityBean, Void>> cellFactory = new javafx.util.Callback<>() {



            @Override
            public TableCell<BookEntityBean, Void> call(TableColumn<BookEntityBean, Void> BookEntityBeanVoidTableColumn) {
                        return new TableCell<BookEntityBean, Void>() {



                    @Override
                    public void updateItem (Void item , boolean empty ) {
                        super.updateItem(item, empty);

                        Text cancella = null;
                        Button editButton = null;

                        HBox managebtn = null;
                        if (empty) {
                            setGraphic(null);
                        } else {
                            cancella = new Text("-");
                            cancella.setFont(Font.font("Arial Rounded MT Bold", 40));
                            cancella.setFill(Color.RED);


                            editButton = new Button("Edit ");

                            editButton.setOnAction(event -> {

                                BookEntityBean book = bookTable.getSelectionModel().getSelectedItem();

                                    SceneSwitcher switcher = new SceneSwitcher();
                                    ArrayList<Object> params = new ArrayList<>();
                                    params.add(book);
                                    try {
                                        switcher.popup("sellBookModule.fxml", "Edit your book", params);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }



                            });


                            cancella.setOnMouseClicked(event -> {
                                try {

                                    BookEntityBean book = bookTable.getSelectionModel().getSelectedItem();
                                    SellBooksUseCaseController controller = new SellBooksUseCaseController();
                                    controller.deleteProduct(book);

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
        }};
        colManage.setCellFactory(cellFactory) ;

        SellBooksUseCaseController controller = new SellBooksUseCaseController() ;


        try {
            bookTable.setItems( controller.getBookList()) ;
        } catch (BookException e) {
            e.printStackTrace();
        }


    }
}
