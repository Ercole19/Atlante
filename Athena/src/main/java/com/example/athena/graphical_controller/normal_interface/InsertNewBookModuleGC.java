package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.beans.BookBean;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.use_case_controllers.ManageYourSellingBooksUCC;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class InsertNewBookModuleGC extends UpdatedShiftImageController implements Initializable , PostInitialize {

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

    private BookBean bookToUpdate;
    private final ManageYourSellingBooksUCC manageYourSellingBooksUCC = new ManageYourSellingBooksUCC();
    private final SceneSwitcher switcher = SceneSwitcher.getInstance();

    public void onConfirmButtonClick()  {


        try {
            BookBean newBook = new BookBean();
            setBeanValues(newBook);
            manageYourSellingBooksUCC.putOnSale(newBook);
            switcher.getTopStage().close();

        }
        catch (ISBNException | BookException e ){
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setContentText(e.getMessage()) ;
            alert.showAndWait() ;
        }
    }

    public void onUpdateButtonClick()
    {

        try {
            BookBean newBook = new BookBean();
            setBeanValues(newBook);
            manageYourSellingBooksUCC.updateProduct(bookToUpdate, newBook);
            switcher.getTopStage().close();
        }
        catch (ISBNException | BookException e){
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setContentText(e.getMessage()) ;
            alert.showAndWait() ;
        }
    }

    private void setBeanValues(BookBean newBook)
    {
        try {
            newBook.setBookTitle(bookTitle.getText());
            newBook.setIsbn(bookISBN.getText());
            newBook.setPrice(bookPrice.getText());
            newBook.setIsNegotiable(bookNegotiability.isSelected());
            newBook.setImage(super.files);
        }
        catch (BookException e)
        {
            SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }



    @FXML
    public void onBackButtonClick() throws IOException {
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

        bookToUpdate = (BookBean) params.get(0);
        bookTitle.setText(bookToUpdate.getBookTitle());
        bookISBN.setText(bookToUpdate.getIsbn());
        bookISBN.setDisable(true);
        bookPrice.setText(bookToUpdate.getPrice());
        bookNegotiability.setSelected(bookToUpdate.getNegotiable());

        confirmButton.setText("Update");
        confirmButton.setOnAction(event -> onUpdateButtonClick());

        for (File file : bookToUpdate.getImage()) {
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
