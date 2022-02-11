package com.example.athena.graphical_controller;

import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.use_case_controllers.SellBooksUseCaseController;
import javax.swing.JFileChooser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.filechooser.FileNameExtensionFilter;

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
    @FXML
    private ImageView bookImage;

    @FXML
    private Button leftArrow ;

    @FXML
    private ImageView leftArrowImage ;

    @FXML
    private Button rightArrow;

    @FXML
    private ImageView rightArrowImage ;

    @FXML
    private Button confirmButton ;

    private List<Image> images ;
    private int index ;
    private List<File> file;

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
    public void onBackButtonClick(ActionEvent event) throws IOException {
        SceneSwitcher switcher = new SceneSwitcher();
        switcher.switcher(event, "bookshop-choose-view.fxml");
    }

    public void onUploadBtnClick(ActionEvent event) {

        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "png");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.images.add(new Image(String.valueOf(fc.getSelectedFile().toURI()))) ;
            this.file.add(fc.getSelectedFile());
            this.bookImage.setImage(images.get(images.size() - 1));
            shiftIndex(images.size() - 1);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.file = new ArrayList<>() ;
        this.images = new ArrayList<>() ;
        disable(leftArrow) ;
        disable(leftArrowImage) ;
        disable(rightArrowImage) ;
        disable(rightArrow) ;
    }

    @Override
    public void postInitialize(ArrayList<Object> params) {
        BookEntityBean bean = (BookEntityBean)params.get(0) ;
        bookTitle.setText(bean.getBookTitle());
        bookISBN.setDisable(true) ;
        bookPrice.setText(bean.getPrice());
        bookNegotiability.setSelected(bean.getNegotiable());

        confirmButton.setText("Update") ;
        confirmButton.setOnAction(this::onUpdateButtonClick) ;
    }
}
