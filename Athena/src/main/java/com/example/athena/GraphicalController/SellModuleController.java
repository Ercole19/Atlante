package com.example.athena.GraphicalController;

import com.example.athena.Exceptions.BookException;
import com.example.athena.Exceptions.ISBNException;
import com.example.athena.UseCaseControllers.SellBooksUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

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
            BookEntityBean book = new BookEntityBean(bookTitle.getText(), bookISBN.getText(), bookPrice.getText(), bookNegotiability.isSelected());
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
