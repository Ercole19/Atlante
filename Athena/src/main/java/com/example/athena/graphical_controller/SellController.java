package com.example.athena.graphical_controller;


import com.example.athena.engineering_classes.observer_pattern.AbstractObserver;
import com.example.athena.entities.BooksSubject;
import com.example.athena.entities.SellerOrBuyerEnum;
import com.example.athena.entities.Student;
import com.example.athena.entities.User;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.SizedAlert;
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

public class SellController implements Initializable, AbstractObserver {



    @FXML
    private TableView<BookBean> bookTable ;

    @FXML
    private TableColumn<BookBean, String> colName ;

    @FXML
    private TableColumn<BookBean, String> colIsbn ;

    @FXML
    private TableColumn<BookBean, Float> colPrice ;

    @FXML
    private TableColumn<BookBean, Void> colManage ;


    private final ObservableList<BookBean> bookList  = FXCollections.observableArrayList() ;
    private final SceneSwitcher switcher = new SceneSwitcher();

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        BooksSubject.getInstance().detachObserver(this);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "bookshop-choose-view.fxml");
    }

    public void onSellBtnClick() {
        if(Student.getInstance().getRepNum() > 50){
            Alert alert = new Alert(Alert.AlertType.WARNING, "You can't sell books anymore, you received too many reports", ButtonType.CLOSE);
            alert.showAndWait();
        }
        else {
            switcher.popup("sellBookModule.fxml", "Sell a book");
            update();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<>("title"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        javafx.util.Callback<TableColumn<BookBean, Void>, TableCell<BookBean, Void>> cellFactory = new javafx.util.Callback<>() {

            @Override
            public TableCell<BookBean, Void> call(TableColumn<BookBean, Void> bookEntityBeanVoidTableColumn) {
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

                                        BookBean book = bookTable.getSelectionModel().getSelectedItem();

                                        ArrayList<Object> params = new ArrayList<>();
                                        params.add(book);

                                        switcher.popup("sellBookModule.fxml", "Edit your book", params);
                                    });

                                    goToBookPage.setOnAction(event -> {
                                        BookBean book = bookTable.getSelectionModel().getSelectedItem();
                                        List<Object> params = new ArrayList<>();
                                        params.add(SellerOrBuyerEnum.SELLER);
                                        params.add(book);

                                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
                                        switcher.switcher(stage, "Book-Page2.fxml", params);

                                    });

                                    cancella.setOnMouseClicked(event -> {
                                        try {

                                            BookBean book = bookTable.getSelectionModel().getSelectedItem();
                                            SellBooksUseCaseController controller = new SellBooksUseCaseController();
                                            controller.deleteProduct(book);

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

        BooksSubject.getInstance().attachObserver(this);
    }

    @Override
    public void update()
    {
        try {
            bookList.clear();
            ObservableList<BookBean> totalBooks;
            totalBooks = BooksSubject.getInstance().getBooksBeansList();
            bookTable.setItems(totalBooks);
        }
        catch (BookException exc)
        {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, exc.getMessage());
            alert.showAndWait();
        }
    }
}
