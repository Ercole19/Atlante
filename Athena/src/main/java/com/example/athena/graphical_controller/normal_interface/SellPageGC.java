package com.example.athena.graphical_controller.normal_interface;


import com.example.athena.beans.BidBean;
import com.example.athena.beans.BookBean;
import com.example.athena.engineering_classes.observer_pattern.AbstractObserver;
import com.example.athena.entities.PersonalBookShelf;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.entities.SellerOrBuyerEnum;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.ManageYourSellingBooksUCC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SellPageGC implements Initializable, AbstractObserver {

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



    private final SceneSwitcher switcher = SceneSwitcher.getInstance();

    @FXML
    protected void onBackButtonClick() throws IOException {
        PersonalBookShelf.getInstance().detachObserver(this);
        switcher.switcher("bookshop-choose-view.fxml");
    }

    public void onSellBtnClick() {
        if(LoggedStudent.getInstance().getCurrentStudent().getRepNum() > 50){
            Alert alert = new Alert(Alert.AlertType.WARNING, "You can't sell books anymore, you received too many reports", ButtonType.CLOSE);
            alert.showAndWait();
        }
        else {
            switcher.popup("sellBookModule.fxml", "Sell a book");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        javafx.util.Callback<TableColumn<BookBean, Void>, TableCell<BookBean, Void>> cellFactory = new javafx.util.Callback<>() {

            @Override
            public TableCell<BookBean, Void> call(TableColumn<BookBean, Void> bookEntityBeanVoidTableColumn) {
                        return new TableCell<>() {


                            @Override
                            public void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);

                                Text delete;
                                Button editButton;
                                Button goToBookPage;
                                Button manageOffers;

                                HBox manageBtn;
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    delete = new Text("-");
                                    delete.setFont(Font.font("Arial Rounded MT Bold", 40));
                                    delete.setFill(Color.RED);


                                    editButton = new Button("Edit ");
                                    goToBookPage = new Button("Book Page");
                                    manageOffers = new Button("Offers");

                                    if (System.getProperty("oracle").equals("true")) {
                                        goToBookPage.setDisable(true);
                                        goToBookPage.setVisible(false);
                                    }

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
                                        switcher.switcher("Book-Page2.fxml", params);

                                    });

                                    delete.setOnMouseClicked(event -> {
                                        try {

                                            BookBean book = bookTable.getSelectionModel().getSelectedItem();
                                            ManageYourSellingBooksUCC controller = new ManageYourSellingBooksUCC();
                                            controller.deleteProduct(book);

                                        } catch (BookException exc) {
                                            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, exc.getMessage());
                                            alert.showAndWait();
                                        }
                                    });

                                    manageOffers.setOnAction(event-> {
                                        BookBean bean = bookTable.getSelectionModel().getSelectedItem();
                                        if (!(bean.getNegotiable())) {
                                            SizedAlert alert = new SizedAlert(Alert.AlertType.WARNING, "Only books with negotiability can receive offers");
                                            alert.showAndWait();
                                        }
                                        else {
                                            List<Object> params = new ArrayList<>();
                                            BidBean bidBean = new BidBean();
                                            bidBean.setBookIsbn(bean.getIsbn());
                                            bidBean.setBookTimestamp(bean.getTimeStamp());
                                            params.add(bidBean);
                                            switcher.switcher("ManageBidsPage.fxml", params);
                                        }
                                    });

                                    manageBtn = new HBox(editButton, delete, goToBookPage, manageOffers);
                                    manageBtn.setStyle("-fx-alignment : center");
                                    HBox.setMargin(editButton, new Insets(2, 2, 0, 3));
                                    HBox.setMargin(delete, new Insets(2, 3, 0, 2));
                                    setGraphic(manageBtn);
                                }
                            }

                        };
        }};
        colManage.setCellFactory(cellFactory) ;

        PersonalBookShelf.getInstance().attachObserver(this);
    }

    @Override
    public void update()
    {
        try {
            ObservableList<BookBean> totalBooks;
            totalBooks = PersonalBookShelf.getInstance().getBooksBeansList();
            bookTable.setItems(totalBooks);
        }
        catch (BookException exc)
        {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, exc.getMessage());
            alert.showAndWait();
        }
    }
}
