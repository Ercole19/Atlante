package com.example.athena.graphical_controller;

import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.use_case_controllers.SellBooksUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;

public class SellModuleController {
    @FXML
    private TextField bookTitle ;
    @FXML
    private TextField bookISBN ;
    @FXML
    private TextField bookPrice ;
    @FXML
    private CheckBox bookNegotiability ;

    public void onConfirmButtonClick() {

        try {
            BookEntityBean book = new BookEntityBean(bookTitle.getText(), bookISBN.getText(), bookPrice.getText(), bookNegotiability.isSelected() , file);
            SellBooksUseCaseController sellBooksUseCaseController = new SellBooksUseCaseController();
            sellBooksUseCaseController.putOnSale(book);
        }
        catch (ISBNException | BookException e){
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setContentText(e.getMessage()) ;
            alert.showAndWait() ;
        }
    }
    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "bookshop-choose-view.fxml");
    }
}
