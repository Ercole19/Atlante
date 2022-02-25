package com.example.athena.graphical_controller;


import com.example.athena.entities.User;
import com.example.athena.exceptions.BookException;
import com.example.athena.use_case_controllers.SellBooksUseCaseController;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SellController implements Initializable {



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


    private final ObservableList<BookEntityBean> bookList  = FXCollections.observableArrayList() ;
    private final SceneSwitcher switcher = new SceneSwitcher();
    private Stage stage;

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "bookshop-choose-view.fxml");
    }

    public void onSellBtnClick() throws IOException, BookException {
        SellBooksUseCaseController controller = new SellBooksUseCaseController();
        if(controller.getTotalReport() > 50){
            Alert alert = new Alert(Alert.AlertType.WARNING, "You can't sell books anymore, you received too many reports", ButtonType.CLOSE);
            alert.showAndWait();
        }
        else {
            switcher.popup("sellBookModule.fxml", "Sell a book");
            refreshTable();
        }
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
                        return new TableCell<>() {


                            @Override
                            public void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);

                                Text cancella;
                                Button editButton;
                                Button goToBookPage;

                                HBox managebtn;
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    cancella = new Text("-");
                                    cancella.setFont(Font.font("Arial Rounded MT Bold", 40));
                                    cancella.setFill(Color.RED);


                                    editButton = new Button("Edit ");
                                    goToBookPage = new Button("Book Page");

                                    editButton.setOnAction(event -> {

                                        BookEntityBean book = bookTable.getSelectionModel().getSelectedItem();

                                        SceneSwitcher switcher = new SceneSwitcher();
                                        ArrayList<Object> params = new ArrayList<>();
                                        params.add(book);

                                        switcher.popup("sellBookModule.fxml", "Edit your book", params);
                                        try {
                                            refreshTable();
                                        } catch (BookException e) {
                                            e.printStackTrace();
                                        }


                                    });


                                    goToBookPage.setOnAction(event -> {
                                        BookEntityBean book = bookTable.getSelectionModel().getSelectedItem();
                                        List<Object> params = new ArrayList<>();
                                        book.setOwner(User.getUser().getEmail());
                                        params.add(book.getOwner());
                                        params.add(book.getIsbn());
                                        params.add(true); //I use this in bookpagecontroller postinitialize, if is true then owner is going to his book page and i disable report and buy butttons
                                        SceneSwitcher switcher = new SceneSwitcher();

                                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
                                        switcher.switcher(stage, "Book-Page2.fxml", params);

                                    });


                                    cancella.setOnMouseClicked(event -> {
                                        try {

                                            BookEntityBean book = bookTable.getSelectionModel().getSelectedItem();
                                            SellBooksUseCaseController controller = new SellBooksUseCaseController();
                                            controller.deleteProduct(book);
                                            refreshTable();

                                        } catch (Exception exc) {
                                            exc.getCause();
                                        }
                                    });


                                    managebtn = new HBox(editButton, cancella, goToBookPage);
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
