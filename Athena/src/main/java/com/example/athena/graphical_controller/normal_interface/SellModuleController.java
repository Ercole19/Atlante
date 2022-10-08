package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.entities.Student;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.beans.normal.BookBean;
import com.example.athena.use_case_controllers.SellBooksUseCaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class SellModuleController extends UpdatedShiftImageController implements Initializable , PostInitialize {

    @FXML
    private TextField bookTitle ;
    @FXML
    private TextField bookISBN ;
    @FXML
    private TextField bookPrice ;
    @FXML
    private CheckBox bookNegotiability ;
    @FXML
    private ImageView bookImage;
    @FXML
    private ImageView leftArrowImage ;
    @FXML
    private ImageView rightArrowImage ;
    @FXML
    private Button confirmButton ;

    private final BookBean book = new BookBean();

    private BookBean oldBook;

    public void onConfirmButtonClick(ActionEvent event)  {


        try {
            setBeanValues();
            SellBooksUseCaseController sellBooksUseCaseController = new SellBooksUseCaseController();
            sellBooksUseCaseController.putOnSale(book);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
            stage.close();
        }
        catch (ISBNException | BookException e ){
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setContentText(e.getMessage()) ;
            alert.showAndWait() ;
        }
    }

    public void onUpdateButtonClick(ActionEvent event)
    {

        try {
            setBeanValues();
            SellBooksUseCaseController sellBooksUseCaseController = new SellBooksUseCaseController();
            sellBooksUseCaseController.updateProduct(oldBook, book);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
            stage.close();
        }
        catch (ISBNException | BookException e){
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setContentText(e.getMessage()) ;
            alert.showAndWait() ;
        }
    }

    private void setBeanValues()
    {
        try {
            this.book.setBookTitle(bookTitle.getText());
            this.book.setIsbn(bookISBN.getText());
            this.book.setPrice(bookPrice.getText());
            this.book.setIsNegotiable(bookNegotiability.isSelected());
            this.book.setImage(super.files);
            this.book.setOwner(Student.getInstance().getEmail());
        }
        catch (BookException e)
        {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }



    @FXML
    public void onBackButtonClick() throws IOException {
        SceneSwitcher switcher = SceneSwitcher.getInstance();
        switcher.switcher("bookshop-choose-view.fxml");
    }

    public void onUploadBtnClick() {
        super.onUploadBtnClick();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.files = new ArrayList<>() ;
        super.images = new ArrayList<>() ;
        super.bookImage = this.bookImage;
        super.leftArrowImage = leftArrowImage ;
        super.rightArrowImage = rightArrowImage ;
        super.shiftIndex(-1);
    }

    @Override
    public void postInitialize(ArrayList<Object> params){

        oldBook = (BookBean) params.get(0);
        bookTitle.setText(oldBook.getBookTitle());
        bookISBN.setText(oldBook.getIsbn());
        bookISBN.setDisable(true);
        bookPrice.setText(oldBook.getPrice());
        bookNegotiability.setSelected(oldBook.getNegotiable());

        confirmButton.setText("Update");
        confirmButton.setOnAction(this::onUpdateButtonClick);

        for (File file : oldBook.getImage()) {
            super.images.add(new Image(String.valueOf(file.toURI())));
            files.add(file);
        }
        if (images.isEmpty()) {
            Image icon = new Image(new File("src/main/resources/assets/upload2.jpg").toURI().toString());
            this.bookImage.setImage(icon);
        } else {
            bookImage.setImage(images.get(0));
            super.shiftIndex(0);
        }
    }

    public void deleteImageOnScreen (){
        super.deleteImageOnScreen();
    }

    @Override
    public void rightArrowClick() {
        super.onRightArrowClick();
    }

    @Override
    public void leftArrowClick() {
        super.onLeftArrowClick();
    }
}
