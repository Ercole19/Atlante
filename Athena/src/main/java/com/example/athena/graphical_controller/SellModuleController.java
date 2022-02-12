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
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class SellModuleController implements Initializable , PostInitialize {
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

    @FXML
    private Button deleteButton;


    private List<Image> images ;
    private int index ;
    private List<File> files;
    private BookEntityBean bean ;

    public void onConfirmButtonClick(ActionEvent event)  {

        try {
            BookEntityBean book = new BookEntityBean(bookTitle.getText(), bookISBN.getText(), bookPrice.getText(), bookNegotiability.isSelected() , files);
            SellBooksUseCaseController sellBooksUseCaseController = new SellBooksUseCaseController();
            sellBooksUseCaseController.putOnSale(book);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
            stage.close();
        }
        catch (ISBNException | BookException e){
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setContentText(e.getMessage()) ;
            alert.showAndWait() ;
        }
    }

    public void onUpdateButtonClick(ActionEvent event)
    {
        try {
            BookEntityBean book = new BookEntityBean(bookTitle.getText(), bookISBN.getText(), bookPrice.getText(), bookNegotiability.isSelected() , files);
            SellBooksUseCaseController sellBooksUseCaseController = new SellBooksUseCaseController();
            sellBooksUseCaseController.updateProduct(book);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
            stage.close();
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

    public void onUploadBtnClick() {

        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "png");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.images.add(new Image(String.valueOf(fc.getSelectedFile().toURI()))) ;
            this.files.add(fc.getSelectedFile());
            this.bookImage.setImage(images.get(images.size() - 1));
            shiftIndex(images.size() - 1);
        }

    }

    public void onLeftArrowClick()
    {
        shiftIndex(--index) ;
        this.bookImage.setImage(this.images.get(this.index)) ;
    }

    public void onRightArrowClick()
    {
        shiftIndex(++index) ;
        this.bookImage.setImage(this.images.get(index)) ;
    }

    private void disable(Node node)
    {
        node.setDisable(true) ;
        node.setVisible(false) ;
    }

    private void enable(Node node)
    {
        node.setVisible(true) ;
        node.setDisable(false) ;
    }

    private void shiftIndex(int position)
    {
        if(position < 0) position = 0 ;
        if(position > this.images.size() -1) position = this.images.size() -1 ;
        this.index = position ;
        checkIndex() ;
    }
    private void checkIndex()
    {
        if(this.index == images.size() -1)
        {
            disable(rightArrow) ;
            disable(rightArrowImage) ;
        }
        else
        {
            enable(rightArrow) ;
            enable(rightArrowImage) ;
        }

        if(this.index == 0)
        {
            disable(leftArrow) ;
            disable(leftArrowImage) ;
        }
        else
        {
            enable(leftArrow);
            enable(leftArrowImage);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.files = new ArrayList<>() ;
        this.images = new ArrayList<>() ;
        disable(leftArrow) ;
        disable(leftArrowImage) ;
        disable(rightArrowImage) ;
        disable(rightArrow) ;
    }

    @Override
    public void postInitialize(ArrayList<Object> params) {
        bean = (BookEntityBean) params.get(0);
        bookTitle.setText(bean.getBookTitle());
        bookISBN.setText(bean.getIsbn());
        bookISBN.setDisable(true);
        bookPrice.setText(bean.getPrice());
        bookNegotiability.setSelected(bean.getNegotiable());

        confirmButton.setText("Update");
        confirmButton.setOnAction(this::onUpdateButtonClick);

        for (File file : bean.getImage()) {
            this.images.add(new Image(String.valueOf(file.toURI())));
            files.add(file);
        }
        if (images.isEmpty()) {
            bookImage.setImage(null);
        } else {

            bookImage.setImage(images.get(images.size() - 1));
            shiftIndex(images.size() - 1);
        }
    }


    public void deleteImage () throws IOException, BookException {

        BookEntityBean book = new BookEntityBean(bookTitle.getText(), bookISBN.getText(), bookPrice.getText(), bookNegotiability.isSelected() , files);

        SellBooksUseCaseController controller = new SellBooksUseCaseController() ;

        File image  = files.get(index) ;
        byte[] fileContent = Files.readAllBytes(image.toPath());


        controller.deleteImage(book, fileContent);
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        stage.close();


    }
}
